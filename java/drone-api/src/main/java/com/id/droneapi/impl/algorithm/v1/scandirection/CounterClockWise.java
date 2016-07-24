package com.id.droneapi.impl.algorithm.v1.scandirection;

import com.id.droneapi.impl.algorithm.v1.Neighborhoods;
import com.id.droneapi.impl.algorithm.v1.Neighborhoods.NodeType;
import com.id.droneapi.impl.algorithm.v1.Node;
import com.id.droneapi.mock.api.DirectionID;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fabrizio Fausitnoni
 */
public class CounterClockWise implements IScanDirectionV1 {

    private static final List<NodeType> VERTICES = new ArrayList<Neighborhoods.NodeType>() {
        {
            add(Neighborhoods.NodeType.NORTHERN_WEST);
            add(Neighborhoods.NodeType.SOUTHERN_WEST);
            add(Neighborhoods.NodeType.SOUTHERN_EST);
            add(Neighborhoods.NodeType.NORTHERN_EST);
        }
    };
    private static final List<DirectionID> DIRECTIONS = new ArrayList<DirectionID>() {
        {
            add(DirectionID.DOWN);
            add(DirectionID.RIGHT);
            add(DirectionID.UP);
            add(DirectionID.LEFT);
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
        currentIndex = (currentIndex == 0) ? size - 1 : currentIndex - 1;

        return currentIndex;
    }

    @Override
    public Map.Entry<Node, DirectionID>
            getStartingNode(Map<NodeType, Node> parentVerticesNodes) {

        List<NodeType> verticesOrdered = getVertexOrdered();
        // FIXME TO UPDATE
        DirectionID currentDirection = DirectionID.DOWN;
        for (Neighborhoods.NodeType nodeType : verticesOrdered) {
            if (parentVerticesNodes.get(nodeType) != null) {
                return new AbstractMap.SimpleEntry(parentVerticesNodes.get(nodeType), currentDirection);
            }
            currentDirection = getNext(currentDirection);
        }

        return null;
    }

}
