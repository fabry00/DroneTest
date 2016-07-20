package com.id.droneapi;

import com.id.droneapi.mock.api.IUrbanizationID;
import com.id.droneapi.mock.api.IDealistaAPI;
import java.util.List;
import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;

/**
 * Interface for the Algorithm to search the Neighborhoods of a node.
 *
 * The algorithm will be injected using this interface. This gives the
 * possibility to change the implementation algorithm if in the feature a better
 * one will be discovered
 *
 * @author Fabrizio Faustinoni
 */
public interface INeighborhoodsAlgorithm {

    /**
     * Given a node's coordinates, a range return the Neighborhoods of the node
     * at that range
     *
     * @param x coordinate X for the inputNode
     * @param y coordinate Y for the inputNode
     * @param range the range to use to get the Neighborhoods
     * @param api the Idealista api
     * @return The list ids
     * @throws NeighborhoodsAlgorithmEx The exception
     */
    List<IUrbanizationID> getNeighborhoods(double x, double y, int range, IDealistaAPI api)
            throws NeighborhoodsAlgorithmEx;

    /**
     * Set the scan direction
     *
     * @param direction the direction
     */
    public void setScandDirection(IScanDirection direction);
}
