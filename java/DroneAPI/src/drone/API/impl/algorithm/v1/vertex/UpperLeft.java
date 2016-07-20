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
 * UpperLeft Vertex
 *
 * @author Fabrizio Faustinoni
 */
public class UpperLeft implements IVertex {

    private static final Logger logger = Logger.getLogger(UpperLeft.class.getName());

    @Override
    public Node getNode(IUrbanizationID sourceNode, IDealistaAPI api) throws NeighborhoodsAlgorithmEx {
        try {
            IUrbanizationID up = api.getAdjacent(sourceNode, DirectionID.UP);
            return new Node(api.getAdjacent(up, DirectionID.LEFT));
        } catch (NoAdjacentNode ex) {
            try {
                IUrbanizationID up = api.getAdjacent(sourceNode, DirectionID.UP);
                return new Node(up);

            } catch (NodeNotFound | DirectionNotFound ex1) {
                throw new NeighborhoodsAlgorithmEx("Oops. something went wrong.", ex1);
            } catch (NoAdjacentNode ex1) {
                // Not an error
            }
        } catch (DirectionNotFound ex) {
            throw new NeighborhoodsAlgorithmEx("Oops. something went wrong", ex);
        } catch (NodeNotFound ex) {
        }

        logger.log(Level.INFO, "UpperLeft vertex not found for node {0}", sourceNode);
        return null;
    }
}
