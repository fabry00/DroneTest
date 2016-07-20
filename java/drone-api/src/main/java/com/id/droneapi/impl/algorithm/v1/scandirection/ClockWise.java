package com.id.droneapi.impl.algorithm.v1.scandirection;

import com.id.droneapi.impl.algorithm.v1.Neighborhoods;
import com.id.droneapi.impl.algorithm.v1.Neighborhoods.NodeType;
import com.id.droneapi.impl.algorithm.v1.Node;
import com.id.droneapi.mock.api.DirectionID;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class ClockWise implements IScanDirectionV1 {

    private static final List<NodeType> VERTICES = new ArrayList<Neighborhoods.NodeType>() {
        {
            add(Neighborhoods.NodeType.NORTHERN_WEST);
            add(Neighborhoods.NodeType.NORTHERN_EST);
            add(Neighborhoods.NodeType.SOUTHERN_EST);
            add(Neighborhoods.NodeType.SOUTHERN_WEST);
        }
    };
    private static final List<DirectionID> DIRECTIONS = new ArrayList<DirectionID>() {
        {
            add(DirectionID.RIGHT);
            add(DirectionID.DOWN);
            add(DirectionID.LEFT);
            add(DirectionID.UP);
        }
    };

    @Override
    public List<DirectionID> getOrderedDir() {
        return DIRECTIONS;
    }

    @Override
    public List<NodeType> getVertexOrdered() {
        return VERTICES;
    }

    @Override
    public DirectionID getNext(DirectionID direction) {
        List<DirectionID> orderedDir = getOrderedDir();
        int index = 0;
        for (DirectionID dir : orderedDir) {
            if (direction.equals(dir)) {
                break;
            }
            index++;
        }

        return index == orderedDir.size() - 1
                ? orderedDir.get(0) : orderedDir.get(index + 1);
    }

    @Override
    public int incrementIndex(int currentIndex, int size) {
        currentIndex = (currentIndex >= size - 1) ? 0 : currentIndex + 1;
        return currentIndex;
    }

    @Override
    public Entry<Node, DirectionID>
            getStartingNode(Map<Neighborhoods.NodeType, Node> parentVerticesNodes) {

        List<NodeType> verticesOrdered = getVertexOrdered();
        DirectionID currentDirection = DirectionID.RIGHT;
        for (Neighborhoods.NodeType nodeType : verticesOrdered) {
            if (parentVerticesNodes.get(nodeType) != null) {
                return new AbstractMap.SimpleEntry(parentVerticesNodes.get(nodeType), currentDirection);
            }
            currentDirection = getNext(currentDirection);
        }

        return null;
    }

}
