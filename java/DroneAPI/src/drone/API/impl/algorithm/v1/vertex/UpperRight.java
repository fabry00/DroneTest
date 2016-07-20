package drone.API.impl.algorithm.v1.vertex;

import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.API.impl.algorithm.v1.Node;
import drone.mock.API.DirectionID;
import drone.mock.API.IDealistaAPI;
import drone.mock.API.IUrbanizationID;
import drone.mock.exception.DirectionNotFound;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.exception.NodeNotFound;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UpperRight Vertex
 *
 * @author Fabrizio Faustinoni
 */
public class UpperRight implements IVertex {
    private static final Logger logger = Logger.getLogger(UpperRight.class.getName());

    @Override
    public Node getNode(IUrbanizationID sourceNode, IDealistaAPI api) throws NeighborhoodsAlgorithmEx {
        try {
            IUrbanizationID up = api.getAdjacent(sourceNode, DirectionID.UP);
            return new Node(api.getAdjacent(up, DirectionID.RIGHT));
        } catch (NoAdjacentNode ex) {
            try {
                /*
                 this catch manage the following situation:
                 --> sourceNode = 01
                 01 02 03            
                 04 05 06
                 07 08 09
                
                 --> return 02

                 */
                IUrbanizationID right = api.getAdjacent(sourceNode, DirectionID.RIGHT);
                return new Node(right);

            } catch (DirectionNotFound ex1) {
                throw new NeighborhoodsAlgorithmEx("Oops. something went wrong", ex1);
            } catch (NodeNotFound | NoAdjacentNode ex1) {
                // Not an error
            }
        } catch (NodeNotFound | DirectionNotFound ex) {
            throw new NeighborhoodsAlgorithmEx("Oops. something went wrong", ex);
        }

        logger.log(Level.INFO, "UpperRight vertex not found for node {0}", sourceNode);
        return null;
    }
}
