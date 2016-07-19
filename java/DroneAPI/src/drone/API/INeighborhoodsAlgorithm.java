package drone.API;

import java.util.List;
import drone.API.exception.NeighborhoodsAlgorithmEx;

public interface INeighborhoodsAlgorithm {
    List<IUrbanizationID> getNeighborhoods(double x, double y, int range, IDealistaAPI api)
            throws NeighborhoodsAlgorithmEx;
}
