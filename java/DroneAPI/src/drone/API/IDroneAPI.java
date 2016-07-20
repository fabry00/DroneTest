package drone.API;

import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.mock.API.IUrbanizationID;
import drone.mock.API.IDealistaAPI;
import java.util.List;

/**
 * Interface that extends the IDealistaAPI adding the required method.
 * 
 * @author Fabrizio Faustinoni
 */
public interface IDroneAPI extends IDealistaAPI{
    
    /**
     * Required function.
     * 
     * obtenerUrbanizaciónes(38.56889, 40.511107, 1) = [id urbanización7, id
     *   urbanización8, id urbanización9, id urbanización12, id urbanización13,
     *   id urbanización17, id urbanización18. id urbanización19]
     * 
     * @param x
     * @param y
     * @param range
     * @return 
     * @throws drone.API.exception.NeighborhoodsAlgorithmEx 
     */
    List<IUrbanizationID> getNeighborhoods(double x, double y, int range)
            throws NeighborhoodsAlgorithmEx;
}
