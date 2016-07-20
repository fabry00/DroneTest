package drone.API.impl.v1;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import drone.mock.API.DirectionID;
import drone.mock.API.IDealistaAPI;
import drone.mock.API.IUrbanizationID;
import drone.mock.exception.DirectionNotFound;
import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.exception.NodeNotFound;

public class Neighborhoods {

    private static final Logger logger = Logger.getLogger(Neighborhoods.class.getName());

    
    public enum NodeType {

        NORTHERN_WEST, SOUTHERN_WEST, SOUTHERN_EST, NORTHERN_EST
    };
    private final Map<NodeType, Node> cardianlNodes = new HashMap<>();
    private final List<NodeType> clockWiseOrdered = new ArrayList<NodeType>() {
        {
            add(NodeType.NORTHERN_WEST);
            add(NodeType.NORTHERN_EST);
            add(NodeType.SOUTHERN_EST);
            add(NodeType.SOUTHERN_WEST);
        }
    };
    private final List<Node> allNodes;
    private final IUrbanizationID centraltNode;

    public Neighborhoods(Node northern_west,
            Node northern_est,
            Node southern_west,
            Node southern_est,
            IUrbanizationID centraltNode,
            List<Node> allNodes) {

        this.centraltNode = centraltNode;
        cardianlNodes.put(NodeType.NORTHERN_WEST, northern_west);
        cardianlNodes.put(NodeType.NORTHERN_EST, northern_est);
        cardianlNodes.put(NodeType.SOUTHERN_WEST, southern_west);
        cardianlNodes.put(NodeType.SOUTHERN_EST, southern_est);
        this.allNodes = allNodes;
    }

    public Node getNorthernWest() {
        return cardianlNodes.get(NodeType.NORTHERN_WEST);
    }

    public Node getNorthernEst() {
        return cardianlNodes.get(NodeType.NORTHERN_EST);
    }

    public Node getSouthernWest() {
        return cardianlNodes.get(NodeType.SOUTHERN_WEST);
    }

    public Node getSouthernEst() {
        return cardianlNodes.get(NodeType.SOUTHERN_EST);
    }

    public List<Node> getNodes() {
        return allNodes;
    }

    public List<IUrbanizationID> getNodesIDs() {
        List<IUrbanizationID> ids = new ArrayList<>();
        for (Node node : allNodes) {
            ids.add(node.getId());
        }
        return ids;
    }

    public Neighborhoods calculateParentNeighborhoods(IDealistaAPI api,
            ScanDirection scandDirection) throws NeighborhoodsAlgorithmEx {

        Map<NodeType, Node> parentCardinalNodes = retreiveParentNeighborhoodsLimits();
        if (!areValidLimits(parentCardinalNodes)) {
            return retrieveValidNeighbors(api, scandDirection);
        } else {
            List<Node> parentNodes = getParentNeighborhoodNodes(api, scandDirection, parentCardinalNodes);

            return new Neighborhoods(parentCardinalNodes.get(NodeType.NORTHERN_WEST),
                    parentCardinalNodes.get(NodeType.NORTHERN_EST),
                    parentCardinalNodes.get(NodeType.SOUTHERN_WEST),
                    parentCardinalNodes.get(NodeType.SOUTHERN_EST),
                    centraltNode,
                    parentNodes);
        }

    }

    private Map<NodeType, Node> retreiveParentNeighborhoodsLimits() throws NeighborhoodsAlgorithmEx {
        Map<NodeType, Node> parentCardianlNodes = new HashMap<>();
        Node northernWest = null;
        Node northernEst = null;
        Node southernEst = null;
        Node southernWest = null;

        try {
            northernWest = getNorthernWest().getUpperLeft();
            logger.log(Level.INFO, "getUpperLeft of: {0} is: {1}",
                    new Object[]{getNorthernWest().getId(), northernWest.getId()});
        } catch (NoAdjacentNode ex) {
            logger.log(Level.INFO, "Limit northernWest not exists for parent neighborhoods");
        }

        try {
            northernEst = getNorthernEst().getUpperRight();
            logger.log(Level.INFO, "getUpperRight of: {0} is: {1}",
                    new Object[]{getNorthernEst().getId(), northernEst.getId()});
        } catch (NoAdjacentNode ex) {
            logger.log(Level.INFO, "Limit northernEst not exists for parent neighborhoods");
        }

        try {
            southernEst = getSouthernEst().getBottomRight();
            logger.log(Level.INFO, "getBottomRight of: {0} is: {1}",
                    new Object[]{getSouthernEst().getId(), southernEst.getId()});
        } catch (NoAdjacentNode ex) {
            logger.log(Level.INFO, "Limit southernEst not exists for parent neighborhoods");
        }

        try {
            southernWest = getSouthernWest().getBottomLeft();
            logger.log(Level.INFO, "getSouthernWest of: {0} is: {1}",
                    new Object[]{getSouthernWest().getId(), southernWest.getId()});
        } catch (NoAdjacentNode ex) {
            logger.log(Level.INFO, "Limit southernWest not exists for parent neighborhoods");
        }

        parentCardianlNodes.put(NodeType.NORTHERN_WEST, northernWest);
        parentCardianlNodes.put(NodeType.NORTHERN_EST, northernEst);
        parentCardianlNodes.put(NodeType.SOUTHERN_EST, southernEst);
        parentCardianlNodes.put(NodeType.SOUTHERN_WEST, southernWest);

        logger.log(Level.INFO, "Cardinal points: N-W:{0} N-E:{1} S-E: {2} S-W:{3}",
                new Object[]{northernWest, northernEst, southernEst, southernWest});

        return parentCardianlNodes;
    }

    private List<Node> getParentNeighborhoodNodes(IDealistaAPI api,
            ScanDirection scandDirection, Map<NodeType, Node> parentCardianlNodes)
            throws NeighborhoodsAlgorithmEx {

        List<Node> neighborhoodNodes = new ArrayList<>();
        // Node limitNode = getInitialLimitNode(scandDirection, parentCardianlNodes);
        Entry<Node, DirectionID> initials = getStartingNode(scandDirection, parentCardianlNodes);
        if (initials == null) {
            logger.log(Level.WARNING, "NeighborhoodNodes empty");
            // No solution
            return neighborhoodNodes;
        }
        Node currentNode = initials.getKey();
        DirectionID currentDir = initials.getValue();
        //Node limitNode = getLimitNode(currentNode, scandDirection, parentCardianlNodes);
        neighborhoodNodes.add(currentNode);
        int startIndex = getStartIndex(currentDir, scandDirection);
        Node limitNode = getLimitNode(startIndex, scandDirection, parentCardianlNodes);

        logger.log(Level.INFO, "StartNode: {0} direction:{1} limitNode: {2} "
                + "startIndex: {3}", new Object[]{currentNode, currentDir,
                    limitNode, startIndex});

        for (int i = startIndex; i < scandDirection.getSizeSequence(); i++) {
            while (true) {
                if (currentNode == null) {
                    break;
                }
                try {
                    IUrbanizationID adjacentId;
                    if (limitNode == null || !limitNode.getId().equals(currentNode.getId())) {
                        adjacentId = api.getAdjacent(currentNode.getId(), currentDir);
                        if (adjacentId.equals(centraltNode)) {
                            // Reached limit
                            currentNode = new Node(centraltNode, api);
                            logger.log(Level.INFO, "Passing for centralNode, skip it. "
                                    + "new CurrentNode:{0}", currentNode);
                            continue;
                        }
                        logger.log(Level.INFO, "Adding node {0} as neighbor", adjacentId);
                        neighborhoodNodes.add(new Node(adjacentId, api));
                    } else {
                        // The currentNode is the same of the cardinal limit
                        adjacentId = currentNode.getId();
                    }
                    currentNode = neighborhoodNodes.get(neighborhoodNodes.size() - 1);
                    if (limitNode != null && limitNode.getId().equals(adjacentId)) {
                        // Reached limit
                        break;
                    }

                } catch (NoAdjacentNode ex) {
                    break;
                } catch (NodeNotFound | DirectionNotFound ex) {
                    throw new NeighborhoodsAlgorithmEx("Oops! Something went wrong");
                }

            }

            currentDir = scandDirection.getNext(currentDir);
            limitNode = getLimitNode(i + 1, scandDirection, parentCardianlNodes);
        }
        // Start node inserted two time, remove the last
        if (neighborhoodNodes.get(0).equals(neighborhoodNodes.get(neighborhoodNodes.size() - 1))) {
            neighborhoodNodes.remove(neighborhoodNodes.size() - 1);
        }

        return neighborhoodNodes;
    }

    private Entry<Node, DirectionID> getStartingNode(ScanDirection scandDirection,
            Map<NodeType, Node> parentCardianlNodes) {

        if (scandDirection.equals(ScanDirection.CLOCKWISE)) {
            DirectionID currentDirection = DirectionID.RIGHT;
            for (NodeType nodeType : clockWiseOrdered) {
                if (parentCardianlNodes.get(nodeType) != null) {
                    return new AbstractMap.SimpleEntry(parentCardianlNodes.get(nodeType), currentDirection);
                }
                currentDirection = scandDirection.getNext(currentDirection);
            }
        } else {
            // FIXME TO UPDATE
            DirectionID currentDirection = DirectionID.DOWN;
            for (int i = clockWiseOrdered.size() - 1; i >= 0; i--) {
                if (parentCardianlNodes.get(clockWiseOrdered.get(i)) != null) {
                    return new AbstractMap.SimpleEntry(parentCardianlNodes.get(clockWiseOrdered.get(i)), currentDirection);
                }
                currentDirection = scandDirection.getNext(currentDirection);
            }
        }

        return null;
    }

    private int getStartIndex(DirectionID currentDir, ScanDirection scandDirection) {
        int i = 0;
        for (DirectionID dir : scandDirection.getList()) {
            if (dir.equals(currentDir)) {
                return i;
            }
            i++;
        }

        return i;
    }

    private Node getLimitNode(int currentIndex, ScanDirection scandDirection,
            Map<NodeType, Node> parentCardianlNodes) {

        if (scandDirection.equals(ScanDirection.CLOCKWISE)) {
            currentIndex = (currentIndex >= clockWiseOrdered.size() - 1)
                    ? 0 : currentIndex + 1;

        } else {
            currentIndex = (currentIndex == 0)
                    ? clockWiseOrdered.size() - 1 : currentIndex - 1;
        }
        NodeType nextCardinalLimit = clockWiseOrdered.get(currentIndex);
        Node newLimitNode = parentCardianlNodes.get(nextCardinalLimit);
        logger.log(Level.INFO, "New Node limit {0}", newLimitNode);
        return newLimitNode;
    }

    private boolean areValidLimits(Map<NodeType, Node> parentCardinalNodes) {
        for(Entry<NodeType, Node> entry : parentCardinalNodes.entrySet()){
            if(entry.getValue() != null){
                return true;
            }
        }
        return true;
    }
    
    private Neighborhoods retrieveValidNeighbors(IDealistaAPI api, ScanDirection scandDirection) {
        //Try to get the Neighborhoods using only the base direction functions
        List<Node> nodes = new ArrayList<>();
        Node northernWest = null;
        Node northernEst = null;
        Node southernEst = null;
        Node southernWest = null;
        
        try {
            IUrbanizationID neighbor = api.getAdjacent(centraltNode, DirectionID.UP);
            northernWest = new Node(neighbor, api);
            nodes.add(northernWest);
        } catch (NoAdjacentNode | NodeNotFound | DirectionNotFound ex) {
            
        }
        try {
            IUrbanizationID neighbor = api.getAdjacent(centraltNode, DirectionID.RIGHT);
            northernEst = new Node(neighbor, api);
            nodes.add(northernEst);
        } catch (NoAdjacentNode | NodeNotFound | DirectionNotFound ex) {
            
        }
        try {
            IUrbanizationID neighbor = api.getAdjacent(centraltNode, DirectionID.DOWN);
            southernEst = new Node(neighbor, api);
            nodes.add(southernEst);
        } catch (NoAdjacentNode | NodeNotFound | DirectionNotFound ex) {
            
        }
        try {
            IUrbanizationID neighbor = api.getAdjacent(centraltNode, DirectionID.LEFT);
            southernWest = new Node(neighbor, api);
             nodes.add(southernWest);
        } catch (NoAdjacentNode | NodeNotFound | DirectionNotFound ex) {
            
        }
        
        
        return new Neighborhoods(northernWest,
                    northernEst,
                    southernWest,
                    southernEst,
                    centraltNode,
                    nodes);
    }


}
