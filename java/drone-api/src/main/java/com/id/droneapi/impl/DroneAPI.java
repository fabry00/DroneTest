package com.id.droneapi.impl;

import java.util.ArrayList;
import java.util.List;
import com.id.droneapi.INeighborhoodsAlgorithm;
import com.id.droneapi.IDroneAPI;
import com.id.droneapi.IScanDirection;
import com.id.droneapi.mock.api.IUrbanizationID;
import com.id.droneapi.mock.api.IDealistaAPI;
import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;
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
     * @param api the Idealista api
     * @param algorithm The algorithm implementation
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


}
