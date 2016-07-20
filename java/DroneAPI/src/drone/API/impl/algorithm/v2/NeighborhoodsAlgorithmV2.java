package drone.API.impl.algorithm.v2;

import java.util.List;
import java.util.logging.Logger;
import drone.mock.API.IDealistaAPI;
import drone.API.INeighborhoodsAlgorithm;
import drone.API.IScanDirection;
import drone.mock.API.IUrbanizationID;
import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.mock.exception.NodeNotFound;
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
