package drone.API.impl.v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import drone.API.DirectionID;
import drone.API.IDealistaAPI;
import drone.API.INeighborhoodsAlgorithm;
import drone.API.IUrbanizationID;
import drone.API.exception.DirectionNotFound;
import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.API.exception.NoAdjacentNode;
import drone.API.exception.NodeNotFound;

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

    /*private Map<Integer, List<IUrbanizationID>> getAllNeighborhoods(IUrbanizationID startingNode,
     int maxRange) throws NeighborhoodsAlgorithmEx {

     Map<Integer, List<IUrbanizationID>> allNeighborhoods = new HashMap<>();

     if (maxRange <= 0) {
     List<IUrbanizationID> nodes = new ArrayList<>();
     allNeighborhoods.put(0, Collections.unmodifiableList(nodes));
     }

     int currentRange = 1;
     while (currentRange <= maxRange) {

     try {
     List<IUrbanizationID> nodes = getNeighborhoodsRange(startingNode,
     maxRange, allNeighborhoods);

     allNeighborhoods.put(currentRange, nodes);

     } catch (NoAdjacentNode ex) {
     logger.log(Level.INFO, "No Neighborhoods found for range: {0} starting form: {1}",
     new Object[]{maxRange, startingNode});
     break;
     } catch (NodeNotFound ex) {
     throw new NeighborhoodsAlgorithmEx("Oops, something went wrong!! Node not found");
     } catch (DirectionNotFound ex) {
     throw new NeighborhoodsAlgorithmEx("Oops, something went wrong! Wrong direction");
     }

     currentRange++;
     }

     return allNeighborhoods;

     }*/

    /*private List<IUrbanizationID> getNeighborhoodsRange(IUrbanizationID startingNode,
     int distanceFromStartNode, Map<Integer, List<IUrbanizationID>> allNeighborhoods)
     throws NoAdjacentNode, NodeNotFound, DirectionNotFound, NeighborhoodsAlgorithmEx {

     List<IUrbanizationID> neighborhoods = new ArrayList<>();
     DirectionID currentDir = STARTING_DIRECTION;

     IUrbanizationID firstNode = this.api.getAdjacent(startingNode, currentDir);
     neighborhoods.add(firstNode);

     logger.log(Level.INFO, "Starting: {0} firstNode: {1} distance: {2}",
     new Object[]{startingNode, firstNode, distanceFromStartNode});

     for (int i = 0; i < SCAN_DIRECTION.getSizeSequence(); i++) {
     currentDir = SCAN_DIRECTION.getNext(currentDir);

     logger.log(Level.INFO, "getNeighborhsForDirection: {0}",
     new Object[]{currentDir});

     //neighborhoods.addAll(getNeighborhsForDirection(startingNode, firstNode,
     //  neighborhoods, currentDir));
     }

     logger.log(Level.INFO, "Starting: {0} distance: {1}, neigh: {2}",
     new Object[]{startingNode, distanceFromStartNode, neighborhoods});

     return neighborhoods;
     }*/

    /*private List<IUrbanizationID> getNeighborhsForDirection(IUrbanizationID startingNode,
     IUrbanizationID currentNode, List<IUrbanizationID> neighborhoods, DirectionID currentDir)
     throws NeighborhoodsAlgorithmEx {

     try {
     IUrbanizationID nextNode = this.api.getAdjacent(currentNode, currentDir);

     if (isANeighbor(nextNode, startingNode, neighborhoods)) {
     neighborhoods.add(nextNode);
     neighborhoods.addAll(getNeighborhsForDirection(startingNode, 
     nextNode, neighborhoods, currentDir));
     }

     } catch (NoAdjacentNode ex) {

     } catch (NodeNotFound ex) {
     throw new NeighborhoodsAlgorithmEx("Oops, something went wrong! Node not found");
     } catch (DirectionNotFound ex) {
     throw new NeighborhoodsAlgorithmEx("Oops, something went wrong! Wrong direction");
     }

     return neighborhoods;

     }*/

    /*private boolean isANeighbor(IUrbanizationID node, IUrbanizationID startingNode,
     List<IUrbanizationID> neighborhoods) {
        
        
     return true;
     }*/
    private Neighborhoods getNeighborhoods(Neighborhoods lastNeighborhoods) throws NeighborhoodsAlgorithmEx {

        
        Neighborhoods parent = lastNeighborhoods.calculateParentNeighborhoods(api,SCAN_DIRECTION);
        
        return parent;
    }
}
