package com.id.droneapi.impl.algorithm.v2.sides;

import com.id.droneapi.impl.algorithm.v2.Node;
import com.id.droneapi.mock.api.DirectionID;
import com.id.droneapi.mock.api.IDealistaAPI;
import com.id.droneapi.mock.api.IUrbanizationID;
import com.id.droneapi.mock.exception.DirectionNotFound;
import com.id.droneapi.mock.exception.NoAdjacentNode;
import com.id.droneapi.mock.exception.NodeNotFound;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Side {

    private static final Logger logger = Logger.getLogger(Side.class.getName());

    protected List<Node> nodes = new ArrayList<>();
    private boolean isFirstSide;
    private ISide.SideType sideType;

    public Collection<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }
    
    public void setIsFirst(boolean isFirstSide) {
        this.isFirstSide = isFirstSide;
    }
    
    public void setType(ISide.SideType sideType) {
        this.sideType = sideType;
    }
    
    public ISide.SideType getSideType(){
        return this.sideType;
    }

    public Collection<IUrbanizationID> getNodesIds() {
        List<IUrbanizationID> ids = new ArrayList<>();
        for (Node node : nodes) {
            ids.add(node.getId());
        }

        return Collections.unmodifiableList(ids);
    }

    protected Entry<IUrbanizationID, Integer> getStartingNode(IDealistaAPI api,
            IUrbanizationID centralNode, int range) {

        logger.log(Level.INFO, "calculating starting node for side: {0}"
                + ". CentralNode: {1}, range; {3}",
                new Object[]{getClass().getSimpleName(), centralNode, range});
        IUrbanizationID currentID = centralNode;

        int failingAttempts = 0;
        boolean found = true;
        for (DirectionID direction : getStartingNodeDirections()) {
            found = true;
            for (int i = 0; i < range; i++) {
                try {
                    currentID = api.getAdjacent(currentID, direction);

                } catch (NodeNotFound | DirectionNotFound ex) {
                    logger.log(Level.SEVERE, null, ex);
                } catch (NoAdjacentNode ex) {
                    logger.log(Level.INFO, "{0} Adjacent node not found for {1}, direction {2}",
                            new Object[]{getClass().getSimpleName(), currentID, direction});

                    // Not an error
                    failingAttempts++;
                    found = false;
                    
                }
            }
        }
        if (found) {
            currentID = (failingAttempts > range) ? null : currentID;
        } else {
            currentID = null;
        }
        return new AbstractMap.SimpleEntry(currentID, failingAttempts);
    }

    public void calculateNeighborhoodsNodes(IDealistaAPI api,
            IUrbanizationID centralNode, int range) {

        Entry<IUrbanizationID, Integer> startNodeEntry = getStartingNode(api,
                centralNode, range);
        IUrbanizationID startNode = startNodeEntry.getKey();

        if (startNode == null) {
            logger.log(Level.INFO, "No nodes fore side {0}. StartNode: {1}, Range: {2}",
                    new Object[]{getClass().getSimpleName(), startNode, range});
            return;
        }
        logger.log(Level.INFO, "starting {0} calculation. StartNode: {1}, Range: {2}",
                new Object[]{getClass().getSimpleName(), startNode, range});

        nodes.add(new Node(startNode));

        IUrbanizationID currentNode = startNode;
        DirectionID sideDir = getSideDirection();
        int max = (range * 2) - startNodeEntry.getValue();
        for (int i = 0; i < max; i++) {
            try {

                Node node = new Node(api.getAdjacent(currentNode, sideDir));

                if (node.getId().equals(centralNode)) {
                    logger.log(Level.INFO, "Reached centralNode {0} stop searching", currentNode);
                    break;
                }
                logger.log(Level.INFO, "Adding node {0} as neighbor", node);
                nodes.add(node);
                currentNode = node.getId();

            } catch (NoAdjacentNode ex) {
                logger.log(Level.INFO, "No adjacent node for {0} direction: {1}",
                        new Object[]{currentNode, sideDir});
                break;
            } catch (NodeNotFound | DirectionNotFound ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
    }

    protected abstract List<DirectionID> getStartingNodeDirections();

    protected abstract DirectionID getSideDirection();

}
