package drone.API.impl.algorithm.v2.sides;

import drone.API.impl.algorithm.v2.Node;
import drone.mock.API.DirectionID;
import drone.mock.API.IDealistaAPI;
import drone.mock.API.IUrbanizationID;
import drone.mock.exception.DirectionNotFound;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.exception.NodeNotFound;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Side {

    private static final Logger logger = Logger.getLogger(Side.class.getName());

    protected List<Node> nodes = new ArrayList<>();
    
     public Collection<Node> getNodes() {
         return Collections.unmodifiableList(nodes);
     }

    public Collection<IUrbanizationID> getNodesIds() {
        List<IUrbanizationID> ids = new ArrayList<>();
        for (Node node : nodes) {
            ids.add(node.getId());
        }

        return Collections.unmodifiableList(ids);
    }

    protected IUrbanizationID getStartingNode(IDealistaAPI api, IUrbanizationID centralNode, int range) {

        IUrbanizationID currentID = centralNode;
        for (DirectionID direction : getStartingNodeDirections()) {
            for (int i = 0; i < range; i++) {
                try {
                    currentID = api.getAdjacent(currentID, direction);
                } catch (NoAdjacentNode | NodeNotFound | DirectionNotFound ex) {
                    Logger.getLogger(BottomSide.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return currentID;
    }

    public void calculateNeighborhoodsNodes(IDealistaAPI api, IUrbanizationID centralNode, int range) {

        IUrbanizationID startNode = getStartingNode(api, centralNode, range);
        DirectionID sideDir = getSideDirection();
        for (int i = 0; i < range; i++) {
            try {
                Node node = new Node(api.getAdjacent(startNode, sideDir));

                logger.log(Level.INFO, "Adding node {0} as neighbor", node);
                nodes.add(node);
                
            } catch (NoAdjacentNode ex) {
                logger.log(Level.INFO, "No adjacent node for {0} direction: {1}",
                        new Object[]{centralNode, sideDir});
            } catch (NodeNotFound | DirectionNotFound ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
    }

    protected abstract List<DirectionID> getStartingNodeDirections();

    protected abstract DirectionID getSideDirection();

}
