package com.id.droneapi.mock;

import java.util.ArrayList;
import com.id.droneapi.mock.exception.NoAdjacentNode;
import com.id.droneapi.mock.api.IDealistaAPI;
import com.id.droneapi.mock.api.IUrbanizationID;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.id.droneapi.mock.api.DirectionID;
import com.id.droneapi.mock.exception.DirectionNotFound;
import com.id.droneapi.mock.exception.NodeAlreadyAdded;
import com.id.droneapi.mock.exception.NodeNotFound;
import com.id.droneapi.mock.direction.DirectionFactory;
import com.id.droneapi.mock.direction.IDirection;

/**
 * Mocked Urbanization UrbanizationMatrix! This class has been implemented for
 * Testing. It could be improved
 *
 * @author Fabrizio Faustinoni
 */
public class UrbanizationMatrix implements IDealistaAPI {

    //! Immutable Map
    private Map<IUrbanizationID, Node> nodes;

    //! Matrix rows
    private final int rows;

    //! Matrix columns
    private final int cols;

    // To create the instance use the Builder
    private UrbanizationMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Given a node's coordinates return the ID of the node
     *
     * @param y coordinate Y for the inputNode
     * @param x coordinate X for the inputNode
     * @return the node id
     * @throws NodeNotFound the exception
     */
    @Override
    public IUrbanizationID getUrbanizationID(double x, double y)
            throws NodeNotFound {
        return getNodeWithCoord(new Coord(x, y)).getId();

    }

    /**
     * Given a node ID and a Direction return the adjacent node
     *
     * @param id the node id
     * @param diretcionID the direction
     * @return the node id
     * @throws NoAdjacentNode The exception
     * @throws NodeNotFound The exception
     * @throws DirectionNotFound The exception
     */
    @Override
    public IUrbanizationID getAdjacent(String id, String diretcionID)
            throws NoAdjacentNode, NodeNotFound, DirectionNotFound {

        DirectionID direcionIDType = getDirectionID(diretcionID);
        IUrbanizationID ubId = new UrbanizationID(id);
        return getAdjacent(ubId, direcionIDType);
    }

    /**
     * Given a node ID and a Direction return the adjacent node
     *
     * @param id the node id
     * @param diretcionID the direction
     * @return the node id
     * @throws NoAdjacentNode The exception
     * @throws NodeNotFound The exception
     * @throws DirectionNotFound The exception
     */
    @Override
    public IUrbanizationID getAdjacent(IUrbanizationID id, DirectionID diretcionID)
            throws NoAdjacentNode, NodeNotFound, DirectionNotFound {
        IDirection direction = getDirectionImpl(diretcionID);

        if (!nodes.containsKey(id)) {
            throw new NodeNotFound();
        }

        Node sourceNode = nodes.get(id);
        Coord coordAdjacent
                = direction.getAdjacentCoord(sourceNode.getCoord(), cols, rows);

        return getNodeWithCoord(coordAdjacent).getId();
    }

    @Override
    public String[][] getUrbanizationMatrix() {
        String[][] matrix = new String[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                try {
                    Node node
                            = getNodeWithCoord(new Coord((double) x, (double) y));
                    matrix[y][x] = node.getId().toString();
                } catch (NodeNotFound ex) {
                    Logger.getLogger(UrbanizationMatrix.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        return matrix;
    }

    /**
     * Print the Matrix in a pretty way
     *
     * @return the String
     */
    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        int maxLengthLine = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                try {
                    Node node
                            = getNodeWithCoord(new Coord((double) x, (double) y));
                    String line = " " + node + " ";
                    maxLengthLine = line.length() > maxLengthLine
                            ? line.length() : maxLengthLine;

                    lines.add(line);
                } catch (NodeNotFound ex) {
                    Logger.getLogger(UrbanizationMatrix.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            lines.add(System.getProperty("line.separator"));
        }

        String output = "";
        for (String line : lines) {
            output += String.format("%" + maxLengthLine + "s", line);
        }
        return output;
    }

    private void setNodes(Map<IUrbanizationID, Node> nodes) {
        this.nodes = Collections.unmodifiableMap(nodes);
    }

    /**
     * Given a node's coordinates return the Node object
     *
     * @param coord
     * @return
     * @throws NodeNotFound
     */
    private Node getNodeWithCoord(Coord coord) throws NodeNotFound {
        for (Entry<IUrbanizationID, Node> entry : nodes.entrySet()) {
            Node node = entry.getValue();
            if (node.getCoord().equals(coord)) {
                return node;
            }
        }
        throw new NodeNotFound();
    }

    /**
     * Convert the String directinID in the specific object representation
     *
     * @param diretcionID
     * @return
     * @throws DirectionNotFound
     */
    private DirectionID getDirectionID(String diretcionID)
            throws DirectionNotFound {
        for (DirectionID dir : DirectionID.values()) {
            if (dir.toString().equals(diretcionID)) {
                return dir;
            }
        }
        throw new DirectionNotFound("Wrong direction spelling: " + diretcionID);
    }

    /**
     * Return the Implementation of a DirectionID
     *
     * @param diretcionID
     * @return
     * @throws DirectionNotFound
     */
    private IDirection getDirectionImpl(DirectionID diretcionID)
            throws DirectionNotFound {
        DirectionFactory factory = new DirectionFactory();
        return factory.createDirection(diretcionID, rows, cols);
    }

    /**
     * Matrix builder
     */
    public static class Builder {

        private final Map<IUrbanizationID, Node> nodes = new HashMap<>();

        public Builder addNode(Node node) throws NodeAlreadyAdded {
            if (nodes.containsKey(node.getId())) {
                throw new NodeAlreadyAdded();
            }
            nodes.put(node.getId(), node);
            return this;
        }

        public UrbanizationMatrix build(int row, int col) {
            UrbanizationMatrix g = new UrbanizationMatrix(row, col);
            g.setNodes(nodes);
            return g;
        }
    }

}
