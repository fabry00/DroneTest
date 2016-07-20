package drone.API.impl.algorithm.v1;

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
import drone.API.impl.algorithm.v1.vertex.BottomLeft;
import drone.API.impl.algorithm.v1.vertex.BottomRight;
import drone.API.impl.algorithm.v1.vertex.IVertex;
import drone.API.impl.algorithm.v1.vertex.UpperLeft;
import drone.API.impl.algorithm.v1.vertex.UpperRight;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.exception.NodeNotFound;
import java.util.Collections;

public class Neighborhoods {

    private static final Logger logger = Logger.getLogger(Neighborhoods.class.getName());

    public enum NodeType {

        NORTHERN_WEST, SOUTHERN_WEST, SOUTHERN_EST, NORTHERN_EST
    };
    private final Map<NodeType, Node> vertices = new HashMap<>();
    private final List<NodeType> clockWiseOrdered = new ArrayList<NodeType>() {
        {
            add(NodeType.NORTHERN_WEST);
            add(NodeType.NORTHERN_EST);
            add(NodeType.SOUTHERN_EST);
            add(NodeType.SOUTHERN_WEST);
        }
    };

    private final IUrbanizationID centralNode;

    private List<Node> neighborNodes = new ArrayList<>();

    public Neighborhoods(Node northern_west,
            Node northern_est,
            Node southern_west,
            Node southern_est,
            IUrbanizationID centraltNode) {

        this.centralNode = centraltNode;
        vertices.put(NodeType.NORTHERN_WEST, northern_west);
        vertices.put(NodeType.NORTHERN_EST, northern_est);
        vertices.put(NodeType.SOUTHERN_WEST, southern_west);
        vertices.put(NodeType.SOUTHERN_EST, southern_est);
    }

    public Node getNorthernWest() {
        return vertices.get(NodeType.NORTHERN_WEST);
    }

    public Node getNorthernEst() {
        return vertices.get(NodeType.NORTHERN_EST);
    }

    public Node getSouthernWest() {
        return vertices.get(NodeType.SOUTHERN_WEST);
    }

    public Node getSouthernEst() {
        return vertices.get(NodeType.SOUTHERN_EST);
    }

    /**
     * Return all the nodes
     *
     * @return
     */
    public List<Node> getNodes() {
        return neighborNodes;
    }

    /**
     * Get the list of IUrbanizationID of all nodes
     *
     * @return
     */
    public List<IUrbanizationID> getNodesIDs() {
        List<IUrbanizationID> ids = new ArrayList<>();
        for (Node node : neighborNodes) {
            ids.add(node.getId());
        }
        return ids;
    }

    /**
     * Calculate the vertices of the parent Neighborhoods
     *
     * @param api
     * @param scandDirection
     * @return
     * @throws NeighborhoodsAlgorithmEx
     */
    public Neighborhoods calculateParentVertices(IDealistaAPI api,
            ScanDirection scandDirection) throws NeighborhoodsAlgorithmEx {

        Map<NodeType, Node> verticesTmp = retreiveParentVertices(api);

        return new Neighborhoods(verticesTmp.get(NodeType.NORTHERN_WEST),
                verticesTmp.get(NodeType.NORTHERN_EST),
                verticesTmp.get(NodeType.SOUTHERN_WEST),
                verticesTmp.get(NodeType.SOUTHERN_EST),
                centralNode);
    }

    /**
     * Calculate all the NeighborhoodsNodes
     *
     * @param parentNeighborhoods
     * @param api
     * @param scandDirection
     * @throws NeighborhoodsAlgorithmEx
     */
    public void calculateNeighborhoodsNodes(Neighborhoods parentNeighborhoods,
            IDealistaAPI api, ScanDirection scandDirection) throws NeighborhoodsAlgorithmEx {

        List<Node> nodes = getNeighborhoodNodes(parentNeighborhoods.getNodesIDs(), api, scandDirection, vertices);
        this.neighborNodes = Collections.unmodifiableList(nodes);
    }

    /**
     * Algorithm to retreive the Neighborhoods parent vertices
     *
     * @return
     * @throws NeighborhoodsAlgorithmEx
     */
    private Map<NodeType, Node> retreiveParentVertices(IDealistaAPI api) throws NeighborhoodsAlgorithmEx {
        Map<NodeType, Node> parentVerticesNodes = new HashMap<>();

        IVertex vertex = new UpperLeft();
        Node northernWest = vertex.getNode(getNorthernWest().getId(), api);

        vertex = new UpperRight();
        Node northernEst = vertex.getNode(getNorthernEst().getId(), api);

        vertex = new BottomRight();
        Node southernEst = vertex.getNode(getSouthernEst().getId(), api);

        vertex = new BottomLeft();
        Node southernWest = vertex.getNode(getSouthernWest().getId(), api);

        parentVerticesNodes.put(NodeType.NORTHERN_WEST, northernWest);
        parentVerticesNodes.put(NodeType.NORTHERN_EST, northernEst);
        parentVerticesNodes.put(NodeType.SOUTHERN_EST, southernEst);
        parentVerticesNodes.put(NodeType.SOUTHERN_WEST, southernWest);

        logger.log(Level.INFO, "Vertices points: N-W:{0} N-E:{1} S-E: {2} S-W:{3}",
                new Object[]{northernWest, northernEst, southernEst, southernWest});

        return parentVerticesNodes;
    }

    private List<Node> getNeighborhoodNodes(List<IUrbanizationID> parentNodes,
            IDealistaAPI api, ScanDirection scandDirection, Map<NodeType, Node> vertices)
            throws NeighborhoodsAlgorithmEx {

        List<Node> neighborhoodNodes = new ArrayList<>();
        Entry<Node, DirectionID> initials = getStartingNode(scandDirection, vertices);
        if (initials == null) {
            logger.log(Level.WARNING, "NeighborhoodNodes empty");
            // No solution
            return neighborhoodNodes;
        }
        Node currentNode = initials.getKey();
        DirectionID currentDir = initials.getValue();
        neighborhoodNodes.add(currentNode);
        int startIndex = getStartIndex(currentDir, scandDirection);
        Node limitNode = getLimitNode(startIndex, scandDirection, vertices);

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
                        if (adjacentId.equals(centralNode)) {
                            // Reached limit
                            currentNode = new Node(centralNode);
                            logger.log(Level.INFO, "Passing for centralNode, skip it. "
                                    + "new CurrentNode:{0}", currentNode);
                            continue;
                        }
                        logger.log(Level.INFO, "Adding node {0} as neighbor", adjacentId);

                        Node nodeToAdd = new Node(adjacentId);
                        if (!parentNodes.contains(adjacentId)) {
                            neighborhoodNodes.add(nodeToAdd);
                        }
                        currentNode = nodeToAdd;
                    } else {
                        // The currentNode is the same of the cardinal limit
                        adjacentId = currentNode.getId();
                    }

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
            limitNode = getLimitNode(i + 1, scandDirection, vertices);
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

}
