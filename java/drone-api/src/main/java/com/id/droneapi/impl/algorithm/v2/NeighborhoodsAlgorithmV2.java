package com.id.droneapi.impl.algorithm.v2;

import java.util.List;
import java.util.logging.Logger;
import com.id.droneapi.mock.api.IDealistaAPI;
import com.id.droneapi.INeighborhoodsAlgorithm;
import com.id.droneapi.IScanDirection;
import com.id.droneapi.mock.api.IUrbanizationID;
import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;
import com.id.droneapi.mock.exception.NodeNotFound;
import java.util.logging.Level;

public class NeighborhoodsAlgorithmV2 implements INeighborhoodsAlgorithm {

    private static final Logger logger = Logger.getLogger(NeighborhoodsAlgorithmV2.class.getName());
    private IDealistaAPI api;

    @Override
    public void setScandDirection(IScanDirection direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IUrbanizationID> getNeighborhoods(double x, double y, int range,
            IDealistaAPI api)
            throws NeighborhoodsAlgorithmEx {

        long start = System.nanoTime();
        this.api = api;

        IUrbanizationID startingNode;
        try {
            startingNode = api.getUrbanizationID(x, y);
        } catch (NodeNotFound ex) {
            throw new NeighborhoodsAlgorithmEx("Oops, something went wrong!! "
                    + "Starting Node not found." + ex.getMessage());
        }

        NeighborhoodsV2 neighborhoods = calculateNeighborhoods(range, startingNode);

        List<IUrbanizationID> ids = neighborhoods.getNodesIDs();
        long elapsedTime = System.nanoTime() - start;

        logger.log(Level.INFO, "Neighborhoods: {0}", new Object[]{ids});

        logger.log(Level.INFO, "Algorithm profile. Range: {0} Time(ms): {1}",
                new Object[]{range, elapsedTime / 1000 / 1000});
        return ids;
    }

    /**
     * Given a range and a starting Node Id calculate the range Neighborhoods
     *
     * @param range
     * @param startingNode
     * @return
     * @throws NeighborhoodsAlgorithmEx
     */
    private NeighborhoodsV2 calculateNeighborhoods(int range, IUrbanizationID startingNode)
            throws NeighborhoodsAlgorithmEx {

        if (range <= 0) {
            throw new NeighborhoodsAlgorithmEx("Wrong range. The range must "
                    + "be a positive integer");
        }

        // Init the starting Neighborhoods
        NeighborhoodsV2 neighborhoods = new NeighborhoodsV2(startingNode);
        neighborhoods.calculateNeighborhoodsNodes(api, range);

        return neighborhoods;
    }

}
