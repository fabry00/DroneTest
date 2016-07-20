package drone.API.impl.algorithm.v2;

import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.API.impl.algorithm.v2.sides.ISide;
import drone.API.impl.algorithm.v2.sides.SideFactory;
import drone.mock.API.IDealistaAPI;
import drone.mock.API.IUrbanizationID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class NeighborhoodsV2 {

    public enum SideType {

        UP, RIGHT, BOTTOM, LEFT
    };

    private static final Logger logger = Logger.getLogger(NeighborhoodsV2.class.getName());

    private final Map<SideType, ISide> sides = new HashMap<>();

    private final IUrbanizationID centralNode;

    public NeighborhoodsV2(IUrbanizationID centraltNode) {
        this.centralNode = centraltNode;

    }
    
    public List<Node> getNodes() {
        List<Node> nodes = new ArrayList<>();
        List<ISide> orderedSides = getOrderedSides();
        for (ISide side : orderedSides) {
            for(Node node : side.getNodes()) {
                // Skipping already addded nodes
                // The vertices are shared for each adjacent side
                if(!nodes.contains(node)){                    
                    nodes.add(node);
                }
            }
        }

        return Collections.unmodifiableList(nodes);
    }

    public List<IUrbanizationID> getNodesIDs() {
        List<IUrbanizationID> nodes = new ArrayList<>();
        for (Node node : getNodes()) {
            nodes.add(node.getId());
        }
        return Collections.unmodifiableList(nodes);
    }
    
    public void calculateNeighborhoodsNodes(IDealistaAPI api, int range)
            throws NeighborhoodsAlgorithmEx {

        SideFactory factory = new SideFactory();
        for (Entry<SideType, ISide> entry : sides.entrySet()) {

            ISide side = factory.createDirection(entry.getKey());

            side.calculateNeighborhoodsNodes(api, centralNode, range);
        }
    }

    private List<ISide> getOrderedSides() {
        List<ISide> sidesOrdered = new ArrayList<>();
        sidesOrdered.add(sides.get(SideType.UP));
        sidesOrdered.add(sides.get(SideType.RIGHT));
        sidesOrdered.add(sides.get(SideType.BOTTOM));
        sidesOrdered.add(sides.get(SideType.LEFT));

        return sidesOrdered;
    }
}
