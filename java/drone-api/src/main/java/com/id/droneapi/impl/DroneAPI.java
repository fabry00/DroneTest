package com.id.droneapi.impl;

import java.util.ArrayList;
import java.util.List;
import com.id.droneapi.mock.API.DirectionID;
import com.id.droneapi.INeighborhoodsAlgorithm;
import com.id.droneapi.IDroneAPI;
import com.id.droneapi.IScanDirection;
import com.id.droneapi.mock.API.IUrbanizationID;
import com.id.droneapi.mock.API.IDealistaAPI;
import com.id.droneapi.mock.exception.DirectionNotFound;
import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;
import com.id.droneapi.mock.exception.NoAdjacentNode;
import com.id.droneapi.mock.exception.NodeNotFound;
import java.util.Collections;

/**
 * Drone API implementation
 *
 * @author Fabrizio Faustinoni
 */
public class DroneAPI implements IDroneAPI {

    private final IDealistaAPI api;
    private final INeighborhoodsAlgorithm algorithm;

    /**
     * Dependency Injection of IDealistaAPI Dependency Injection of
     * INeighborhoodsAlgorithm
     *
     * @param api
     * @param algorithm
     */
    public DroneAPI(IDealistaAPI api, INeighborhoodsAlgorithm algorithm) {
        this.api = api;
        this.algorithm = algorithm;
    }

    @Override
    public List<IUrbanizationID> getNeighborhoods(double x, double y, int range)
            throws NeighborhoodsAlgorithmEx {
        List<IUrbanizationID> nodes = new ArrayList<>();

        nodes.addAll(this.algorithm.getNeighborhoods(x, y, range, api));

        return Collections.unmodifiableList(nodes);
    }

    @Override
    public List<IUrbanizationID> getNeighborhoods(double x, double y, int range, IScanDirection direction) throws NeighborhoodsAlgorithmEx {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
