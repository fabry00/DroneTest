package drone.API.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import drone.mock.API.DirectionID;
import drone.API.INeighborhoodsAlgorithm;
import drone.API.IDroneAPI;
import drone.mock.API.IUrbanizationID;
import drone.mock.API.IDealistaAPI;
import drone.mock.exception.DirectionNotFound;
import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.exception.NodeNotFound;
import java.util.Collections;

/**
 * Drone API implementation
 * @author Fabrizio Faustinoni
 */
public class DroneAPI implements IDroneAPI {

    private final IDealistaAPI api;
    private final INeighborhoodsAlgorithm algorithm;
    
    /**
     * Dependency Injection of IDealistaAPI
     * Dependency Injection of INeighborhoodsAlgorithm
     * @param api 
     * @param algorithm 
     */
    public DroneAPI(IDealistaAPI api, INeighborhoodsAlgorithm algorithm) {
        this.api = api;
        this.algorithm = algorithm;
    }

    @Override
    public List<IUrbanizationID> getNeighborhoods(double x, double y, int range)
                throws NeighborhoodsAlgorithmEx{
        List<IUrbanizationID> nodes = new ArrayList<>();
        
        nodes.addAll(this.algorithm.getNeighborhoods(x, y, range, api));
        
        return Collections.unmodifiableList(nodes);
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
