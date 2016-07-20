package drone.API.impl.v1.vertex;

import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.API.impl.v1.Node;
import drone.mock.API.DirectionID;
import drone.mock.API.IDealistaAPI;
import drone.mock.API.IUrbanizationID;
import drone.mock.exception.DirectionNotFound;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.exception.NodeNotFound;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BottomRight Vertex
 * @author Fabrizio Faustinoni
 */
public class BottomRight implements IVertex{
    
    private static final Logger logger = Logger.getLogger(BottomRight.class.getName());

    @Override
    public Node getNode(IUrbanizationID sourceNode, IDealistaAPI api) throws NeighborhoodsAlgorithmEx {
        try {
            IUrbanizationID bottom = api.getAdjacent(sourceNode, DirectionID.DOWN);
            return new Node(api.getAdjacent(bottom, DirectionID.RIGHT));
        } catch (NoAdjacentNode ex) {
            try {
                /*
                 this catch manage the following situation:
                 --> sourceNode = 03
                 01 02 03            
                 04 05 06
                 07 08 09
                
                 --> return 06
                 */
                try {
                    IUrbanizationID down = api.getAdjacent(sourceNode, DirectionID.DOWN);
                    return new Node(down);
                } catch (NoAdjacentNode ex2) {
                    /*
                     this catch manage the following situation:
                     --> sourceNode = 07
                     01 02 03            
                     04 05 06
                     07 08 09
                
                     --> return 08
                     */
                    IUrbanizationID right = api.getAdjacent(sourceNode, DirectionID.RIGHT);
                    return new Node(right);
                }
            } catch (DirectionNotFound ex1) {
                throw new NeighborhoodsAlgorithmEx("Oops. something went wrong", ex1);
            } catch (NoAdjacentNode | NodeNotFound ex1) {
                // Not an error
            }
        } catch (DirectionNotFound ex) {
            throw new NeighborhoodsAlgorithmEx("Oops. something went wrong", ex);
        } catch (NodeNotFound ex2) {
            // Not an error
        }

        logger.log(Level.INFO, "BottomRight vertex not found for node {0}", sourceNode);
        return null;
    }
}
