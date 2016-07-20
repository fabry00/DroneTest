package drone.API.impl.v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import drone.mock.API.DirectionID;
import drone.mock.API.IDealistaAPI;
import drone.API.INeighborhoodsAlgorithm;
import drone.mock.API.IUrbanizationID;
import drone.mock.exception.DirectionNotFound;
import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.exception.NodeNotFound;

public class NeighborhoodsAlgorithm implements INeighborhoodsAlgorithm {

    private static final DirectionID STARTING_DIRECTION = DirectionID.UP;
    private static final ScanDirection SCAN_DIRECTION = ScanDirection.CLOCKWISE;

    private IDealistaAPI api;
    private static final Logger logger = Logger.getLogger(NeighborhoodsAlgorithm.class.getName());

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
            throw new NeighborhoodsAlgorithmEx("Oops, something went wrong!! Starting Node not found");
        }
        Map<Integer, Neighborhoods> allNeighborhoods = getAllNeighborhoods(range, startingNode);

        Neighborhoods neighborhoods = allNeighborhoods.get(range);
        /*Map<Integer, List<IUrbanizationID>> allNeighborhoods
         = getAllNeighborhoods(startingNode, range);*/
        // return allNeighborhoods.get(range).getNodes();
        List<IUrbanizationID> ids =  neighborhoods.getNodesIDs();
        long elapsedTime = System.nanoTime() - start;
        System.out.println("**** Range: "+range+" Profile: "+elapsedTime/1000/1000+" ms ****");
        return ids;
    }

    private Map<Integer, Neighborhoods> getAllNeighborhoods(int maxRange, IUrbanizationID startingNode)
            throws NeighborhoodsAlgorithmEx {

        Map<Integer, Neighborhoods> allNeighborhoods = new HashMap<>();

        if (maxRange <= 0) {
            throw new NeighborhoodsAlgorithmEx("Wrong range");
        }

        allNeighborhoods.put(0, new Neighborhoods(new Node(startingNode, api),
                new Node(startingNode, api),
                new Node(startingNode, api),
                new Node(startingNode, api),
                startingNode,
                new ArrayList<Node>())
        );

        int currentRange = 1;

        while (currentRange <= maxRange) {
            Neighborhoods lastNeighborhoods = allNeighborhoods.get(currentRange - 1);
            allNeighborhoods.put(currentRange, getNeighborhoods(lastNeighborhoods));
            currentRange++;
        }

        return allNeighborhoods;
    }

    
    private Neighborhoods getNeighborhoods(Neighborhoods lastNeighborhoods) throws NeighborhoodsAlgorithmEx {

        
        Neighborhoods parent = lastNeighborhoods.calculateParentNeighborhoods(api,SCAN_DIRECTION);
        
        return parent;
    }
}
