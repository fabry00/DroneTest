package drone.mock;

import java.util.ArrayList;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.API.IDealistaAPI;
import drone.mock.API.IUrbanizationID;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import drone.mock.API.DirectionID;
import drone.mock.exception.DirectionNotFound;
import drone.mock.exception.NodeAlreadyAdded;
import drone.mock.exception.NodeNotFound;
import drone.mock.direction.DirectionFactory;
import drone.mock.direction.IDirection;

/**
 * Mocked Urbanization UrbanizationMatrix!
 * This class has been implemented for Testing.
 * It could be improved
 * 
 * @author Fabrizio Faustinoni
 */
public class UrbanizationMatrix implements IDealistaAPI {

    //! Immutable Map
    private Map<IUrbanizationID, Node> nodes;
    
    //! Matrix rows
    private final int rows;
    
    //! Matrix columns
    private final int col;

    // To create the instance use the Builder
    private UrbanizationMatrix(int rows, int cols) {
        this.rows = rows;
        this.col = cols;
    }

    /**
     * Given a node's coordinates return the ID of the node
     * @param x
     * @param y
     * @return
     * @throws NodeNotFound 
     */
    @Override
    public IUrbanizationID getUrbanizationID(double x, double y)
            throws NodeNotFound {
        return getNodeWithCoord(new Coord(x, y)).getId();

    }

    /**
     * Given a node ID and a Direction return the adjacent node
     * @param id
     * @param diretcionID
     * @return
     * @throws NoAdjacentNode
     * @throws NodeNotFound
     * @throws DirectionNotFound 
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
     * @param id
     * @param diretcionID
     * @return
     * @throws NoAdjacentNode
     * @throws NodeNotFound
     * @throws DirectionNotFound 
     */
    @Override
    public IUrbanizationID getAdjacent(IUrbanizationID id, DirectionID diretcionID)
            throws NoAdjacentNode, NodeNotFound, DirectionNotFound {
        IDirection direction = getDirectionImpl(diretcionID);
        
        if(!nodes.containsKey(id)) {
            throw new NodeNotFound();
        }
        
        Node sourceNode = nodes.get(id);
        Coord coordAdjacent 
                = direction.getAdjacentCoord(sourceNode.getCoord(), col, rows);
        
        return getNodeWithCoord(coordAdjacent).getId();
    }

    /**
     * Print the Matrix in a pretty way 
     * @return 
     */
    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        int maxLengthLine = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < col; x++) {
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
     * @param diretcionID
     * @return
     * @throws DirectionNotFound 
     */
    private IDirection getDirectionImpl(DirectionID diretcionID)
            throws DirectionNotFound {
        DirectionFactory factory = new DirectionFactory();
        return factory.createDirection(diretcionID, rows, col);
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
