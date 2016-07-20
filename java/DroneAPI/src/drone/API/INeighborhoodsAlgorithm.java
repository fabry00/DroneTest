package drone.API;

import drone.mock.API.IUrbanizationID;
import drone.mock.API.IDealistaAPI;
import java.util.List;
import drone.API.exception.NeighborhoodsAlgorithmEx;

/**
 * Interface for the Algorithm to search the Neighborhoods of a node.
 * 
 * The algorithm will be injected using this interface. This gives the possibility
 * to change the implementantion algorithm if in the feauture a better one will
 * be discovered
 * 
 * @author Fabrizio Faustinoni
 */
public interface INeighborhoodsAlgorithm {
    
    /**
     * Given a node's coordinates, a range return the Neighborhoods of the node
     * at that range
     * @param x
     * @param y
     * @param range
     * @param api
     * @return
     * @throws NeighborhoodsAlgorithmEx 
     */
    List<IUrbanizationID> getNeighborhoods(double x, double y, int range, IDealistaAPI api)
            throws NeighborhoodsAlgorithmEx;
}
