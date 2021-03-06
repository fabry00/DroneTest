package com.id.droneapi.impl.algorithm.v1;

import java.util.List;
import java.util.logging.Logger;
import com.id.droneapi.mock.api.IDealistaAPI;
import com.id.droneapi.INeighborhoodsAlgorithm;
import com.id.droneapi.IScanDirection;
import com.id.droneapi.mock.api.IUrbanizationID;
import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;
import com.id.droneapi.impl.algorithm.v1.scandirection.ClockWise;
import com.id.droneapi.impl.algorithm.v1.scandirection.IScanDirectionV1;
import com.id.droneapi.mock.exception.NodeNotFound;
import java.util.logging.Level;

public class NeighborhoodsAlgorithmV1 implements INeighborhoodsAlgorithm {

    private static IScanDirection SCAN_DIRECTION = new ClockWise();

    private static final Logger logger = Logger.getLogger(NeighborhoodsAlgorithmV1.class.getName());
    private IDealistaAPI api;
    private IScanDirection scanDirection = SCAN_DIRECTION;

    /**
     * Constructor
     * @param scanDirection the scan direction implemetnation
     */
    public NeighborhoodsAlgorithmV1(IScanDirection scanDirection) {
        if (scanDirection != null) {
            this.scanDirection = scanDirection;
        }
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

        Neighborhoods neighborhoods = calculateNeighborhoods(range, startingNode);

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
    private Neighborhoods calculateNeighborhoods(int range, IUrbanizationID startingNode)
            throws NeighborhoodsAlgorithmEx {

        if (range <= 0) {
            throw new NeighborhoodsAlgorithmEx("Wrong range. The range must "
                    + "be a positive integer");
        }

        // Init the starting Neighborhoods
        Neighborhoods lastNeighborhoods = initStartingNeighborhoods(startingNode, api);

        int currentRange = 1;
        // Calculating the Neighborhoods until we reach the desired range
        while (currentRange <= range) {
            // Calcuate the new Neighborhoods Vertex
            Neighborhoods newNeighborhoods = lastNeighborhoods.calculateParentVertices(api, scanDirection);
            // Calculate the NeighborhoodsNodes
            newNeighborhoods.calculateNeighborhoodsNodes(lastNeighborhoods, api,
                    (IScanDirectionV1) scanDirection);

            lastNeighborhoods = newNeighborhoods;
            currentRange++;
        }

        return lastNeighborhoods;
    }

    /**
     * Initiazlie the starting Neighborhoods. The starting Neighborhoods is
     * represented by the starting node and as vertices the same startingNode
     *
     * @param startingNode
     * @param api
     * @return
     */
    private Neighborhoods initStartingNeighborhoods(IUrbanizationID startingNode, IDealistaAPI api) {
        return new Neighborhoods(new Node(startingNode),
                new Node(startingNode),
                new Node(startingNode),
                new Node(startingNode),
                startingNode);
    }

}
