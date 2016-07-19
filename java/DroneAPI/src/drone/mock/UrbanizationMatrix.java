package drone.mock;

import java.util.ArrayList;
import drone.API.exception.NoAdjacentNode;
import drone.API.IDealistaAPI;
import drone.API.IUrbanizationID;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import drone.API.DirectionID;
import drone.API.exception.DirectionNotFound;
import drone.API.exception.NodeAlreadyAdded;
import drone.API.exception.NodeNotFound;
import drone.mock.direction.DirectionFactory;
import drone.mock.direction.IDirection;

/**
 * Mocked Graph WARNING!! This is just a TestClass. It is not complete and not
 * optional
 */
public class UrbanizationMatrix implements IDealistaAPI {

    // Immutable Map
    private Map<IUrbanizationID, Node> nodes;
    private final int row;
    private final int col;

    // To create the instance use the Builder
    private UrbanizationMatrix(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public IUrbanizationID getUrbanizationID(double x, double y)
            throws NodeNotFound {
        return getNodeWithCoord(new Coord(x, y)).getId();

    }

    @Override
    public IUrbanizationID getAdjacent(String id, String diretcionID)
            throws NoAdjacentNode, NodeNotFound, DirectionNotFound {

        DirectionID direcionIDType = getDirectionID(diretcionID);
        IUrbanizationID ubId = new UrbanizationID(id);
        return getAdjacent(ubId, direcionIDType);
    }

    @Override
    public IUrbanizationID getAdjacent(IUrbanizationID id, DirectionID diretcionID)
            throws NoAdjacentNode, NodeNotFound, DirectionNotFound {
        IDirection direction = getDirectionImpl(diretcionID);
        
        if(!nodes.containsKey(id)) {
            throw new NodeNotFound();
        }
        
        Node sourceNode = nodes.get(id);
        Coord coordAdjacent 
                = direction.getAdjacentCoord(sourceNode.getCoord(), col, row);
        
        return getNodeWithCoord(coordAdjacent).getId();
    }

    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        int maxLengthLine = 0;
        for (int y = 0; y < row; y++) {
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

    private Node getNodeWithCoord(Coord coord) throws NodeNotFound {
        for (Entry<IUrbanizationID, Node> entry : nodes.entrySet()) {
            Node node = entry.getValue();
            if (node.getCoord().equals(coord)) {
                return node;
            }
        }
        throw new NodeNotFound();
    }

    private DirectionID getDirectionID(String diretcionID)
            throws DirectionNotFound {
        for (DirectionID dir : DirectionID.values()) {
            if (dir.toString().equals(diretcionID)) {
                return dir;
            }
        }
        throw new DirectionNotFound("Wrong direction spelling: " + diretcionID);
    }

    private IDirection getDirectionImpl(DirectionID diretcionID)
            throws DirectionNotFound {
        DirectionFactory factory = new DirectionFactory();
        return factory.createDirection(diretcionID, row, col);
    }

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
