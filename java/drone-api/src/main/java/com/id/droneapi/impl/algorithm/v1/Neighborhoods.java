package com.id.droneapi.impl.algorithm.v1;

import com.id.droneapi.IScanDirection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.id.droneapi.mock.api.DirectionID;
import com.id.droneapi.mock.api.IDealistaAPI;
import com.id.droneapi.mock.api.IUrbanizationID;
import com.id.droneapi.mock.exception.DirectionNotFound;
import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;
import com.id.droneapi.impl.algorithm.v1.scandirection.IScanDirectionV1;
import com.id.droneapi.impl.algorithm.v1.vertex.BottomLeft;
import com.id.droneapi.impl.algorithm.v1.vertex.BottomRight;
import com.id.droneapi.impl.algorithm.v1.vertex.IVertex;
import com.id.droneapi.impl.algorithm.v1.vertex.UpperLeft;
import com.id.droneapi.impl.algorithm.v1.vertex.UpperRight;
import com.id.droneapi.mock.exception.NoAdjacentNode;
import com.id.droneapi.mock.exception.NodeNotFound;
import java.util.Collections;

public class Neighborhoods {

    private static final Logger logger = Logger.getLogger(Neighborhoods.class.getName());

    public enum NodeType {

        NORTHERN_WEST, SOUTHERN_WEST, SOUTHERN_EST, NORTHERN_EST
    };
    private final Map<NodeType, Node> vertices = new HashMap<>();

    private final IUrbanizationID centralNode;

    private List<Node> neighborNodes = new ArrayList<>();

    public Neighborhoods(Node northern_west, Node northern_est, Node southern_west,
            Node southern_est, IUrbanizationID centraltNode) {

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
     * @return the list
     */
    public List<Node> getNodes() {
        return neighborNodes;
    }

    /**
     * Get the list of IUrbanizationID of all nodes
     *
     * @return the list
     */
    public List<IUrbanizationID> getNodesIDs() {
        List<IUrbanizationID> ids = new ArrayList<>();
        neighborNodes.stream().forEach((node) -> {
            ids.add(node.getId());
        });
        return ids;
    }

    /**
     * Calculate the vertices of the parent Neighborhoods
     *
     * @param api the Idealista Api
     * @param scandDirection the scan direction
     * @return the Neighborhoods
     * @throws NeighborhoodsAlgorithmEx the exception
     */
    public Neighborhoods calculateParentVertices(IDealistaAPI api,
            IScanDirection scandDirection) throws NeighborhoodsAlgorithmEx {

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
     * @param parentNeighborhoods the parent Neighborhoods
     * @param api the Idealista API
     * @param scandDirection the scan direction
     * @throws NeighborhoodsAlgorithmEx the exception
     */
    public void calculateNeighborhoodsNodes(Neighborhoods parentNeighborhoods,
            IDealistaAPI api, IScanDirectionV1 scandDirection) throws NeighborhoodsAlgorithmEx {

        List<Node> nodes = getNeighborhoodNodes(parentNeighborhoods.getNodesIDs(),
                api, scandDirection, vertices);

        this.neighborNodes = Collections.unmodifiableList(nodes);
    }

    /**
     * Algorithm to retrieve the Neighborhoods parent vertices
     *
     * @return The  Neighborhoods parent vertices map 
     * @throws NeighborhoodsAlgorithmEx the exception
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
            IDealistaAPI api, IScanDirectionV1 scandDirection, Map<NodeType, Node> vertices)
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
        Node currentVertex = getNextVertex(startIndex, scandDirection, vertices);

        logger.log(Level.INFO, "StartNode: {0} direction:{1} limitNode: {2} "
                + "startIndex: {3}", new Object[]{currentNode, currentDir,
                    currentVertex, startIndex});

        for (int i = startIndex; i < scandDirection.getVertexOrdered().size(); i++) {
            while (true) {
                if (currentNode == null) {
                    break;
                }
                try {
                    IUrbanizationID adjacentId;
                    if (currentVertex == null || !currentVertex.getId().equals(currentNode.getId())) {
                        adjacentId = api.getAdjacent(currentNode.getId(), currentDir);
                        if (adjacentId.equals(centralNode)) {
                            // Limit Reached
                            currentNode = new Node(centralNode);
                            logger.log(Level.INFO, "Passing for centralNode, skip it. "
                                    + "new CurrentNode:{0}", currentNode);
                            continue;
                        }

                        Node nodeToAdd = new Node(adjacentId);
                        if (!parentNodes.contains(adjacentId)) {
                            logger.log(Level.INFO, "Adding node {0} as neighbor", adjacentId);
                            neighborhoodNodes.add(nodeToAdd);
                        }
                        currentNode = nodeToAdd;
                    } else {
                        // The currentNode is the same of the cardinal limit
                        adjacentId = currentNode.getId();
                    }

                    if (currentVertex != null && currentVertex.getId().equals(adjacentId)) {
                        // Reached limit
                        break;
                    }

                } catch (NoAdjacentNode ex) {
                    break;
                } catch (NodeNotFound | DirectionNotFound ex) {
                    throw new NeighborhoodsAlgorithmEx("Oops! Something went wrong ", ex);
                }

            }

            currentDir = scandDirection.getNext(currentDir);
            currentVertex = getNextVertex(i + 1, scandDirection, vertices);
        }
        // Start node inserted two times, remove the last
        if (neighborhoodNodes.get(0).equals(neighborhoodNodes.get(neighborhoodNodes.size() - 1))) {
            neighborhoodNodes.remove(neighborhoodNodes.size() - 1);
        }

        return neighborhoodNodes;
    }

    private Entry<Node, DirectionID> getStartingNode(IScanDirectionV1 scandDirection,
            Map<NodeType, Node> parentVerticesNodes) {

        return scandDirection.getStartingNode(parentVerticesNodes);

    }

    private int getStartIndex(DirectionID currentDir, IScanDirectionV1 scandDirection) {
        int i = 0;
        for (DirectionID dir : scandDirection.getOrderedDir()) {
            if (dir.equals(currentDir)) {
                return i;
            }
            i++;
        }

        return i;
    }

    private Node getNextVertex(int currentIndex, IScanDirectionV1 scandDirection,
            Map<NodeType, Node> parentCardianlNodes) {

        int max = scandDirection.getVertexOrdered().size();
        currentIndex
                = scandDirection.incrementIndex(currentIndex, max);

        NodeType nextCardinalLimit = scandDirection.getVertexOrdered().get(currentIndex);
        Node newLimitNode = parentCardianlNodes.get(nextCardinalLimit);
        logger.log(Level.INFO, "New Node limit {0}", newLimitNode);
        return newLimitNode;
    }

}
