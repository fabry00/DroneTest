package drone.API;

import java.util.List;

public interface ITestDroneAPI extends IDealistaAPI{
    
    List<IUrbanizationID> getNeighborhoods(double x, double y, int range);
}
