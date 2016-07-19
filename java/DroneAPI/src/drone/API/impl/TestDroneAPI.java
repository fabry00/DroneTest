package drone.API.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import drone.API.DirectionID;
import drone.API.INeighborhoodsAlgorithm;
import drone.API.ITestDroneAPI;
import drone.API.IUrbanizationID;
import drone.API.IDealistaAPI;
import drone.API.exception.DirectionNotFound;
import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.API.exception.NoAdjacentNode;
import drone.API.exception.NodeNotFound;

public class TestDroneAPI implements ITestDroneAPI {

    private final IDealistaAPI api;
    private final INeighborhoodsAlgorithm algorithm;
    /**
     * Dependency Injection of IDealistaAPI
     * Dependency Injection of INeighborhoods
     * @param api 
     * @param algorithm 
     */
    public TestDroneAPI(IDealistaAPI api, INeighborhoodsAlgorithm algorithm) {
        this.api = api;
        this.algorithm = algorithm;
    }

    @Override
    public List<IUrbanizationID> getNeighborhoods(double x, double y, int range) {
        try {
            return this.algorithm.getNeighborhoods(x, y, range, api);
        } catch (NeighborhoodsAlgorithmEx ex) {
            Logger.getLogger(TestDroneAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<>();
    }

    @Override
    public IUrbanizationID getUrbanizationID(double x, double y)
            throws NodeNotFound {
        return this.api.getUrbanizationID(x, y);
    }

    @Override
    public IUrbanizationID getAdjacent(String id, String diretcionID)
            throws NoAdjacentNode, NodeNotFound, DirectionNotFound {
        return this.api.getAdjacent(id, diretcionID);
    }

    @Override
    public IUrbanizationID getAdjacent(IUrbanizationID id, DirectionID diretcionID)
            throws NoAdjacentNode, NodeNotFound, DirectionNotFound {
        return this.api.getAdjacent(id, diretcionID);
    }

}
