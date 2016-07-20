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
 * Bottom left Vertex
 * @author Fabrizio Faustinoni
 */
public class BottomLeft implements IVertex {

    private static final Logger logger = Logger.getLogger(BottomLeft.class.getName());
    
    @Override
    public Node getNode(IUrbanizationID sourceNode, IDealistaAPI api) throws NeighborhoodsAlgorithmEx {
        try {
            IUrbanizationID bottom = api.getAdjacent(sourceNode, DirectionID.DOWN);
            return new Node(api.getAdjacent(bottom, DirectionID.LEFT));
        } catch (NoAdjacentNode ex) {
            try {
                /*
                 this catch manage the following situation:
                 --> this.id = 01
                 01 02 03            
                 04 05 06
                 07 08 09
                
                 --> return 04
                 */
                IUrbanizationID down = api.getAdjacent(sourceNode, DirectionID.DOWN);
                return new Node(down);

            } catch (NoAdjacentNode ex1) {
                try {
                    /*
                     this catch manage the following situation:
                     --> this.id = 09
                     01 02 03            
                     04 05 06
                     07 08 09

                     --> return 08
                     */
                    IUrbanizationID left = api.getAdjacent(sourceNode, DirectionID.LEFT);
                    return new Node(left);
                } catch (NodeNotFound | DirectionNotFound ex2) {
                    throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
                } catch (NoAdjacentNode ex2) {
                    // Not an error
                }
            } catch (NodeNotFound | DirectionNotFound ex1) {
                throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
            }
        } catch (DirectionNotFound ex) {
            throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
        } catch (NodeNotFound ex2) {
            // Not an error
        }

        logger.log(Level.INFO, "BottomLeft vertex not found for node {0}", sourceNode);
        return null;
    }
    
    
}
