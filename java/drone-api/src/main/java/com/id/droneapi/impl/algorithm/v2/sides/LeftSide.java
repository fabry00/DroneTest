package com.id.droneapi.impl.algorithm.v2.sides;

import com.id.droneapi.mock.api.DirectionID;
import java.util.ArrayList;
import java.util.List;

public class LeftSide extends Side implements ISide{

    private static final List<DirectionID> STARTING_NODE_DIRECTIONS = new ArrayList<DirectionID>() {
        {
            add(DirectionID.DOWN);
            add(DirectionID.LEFT);
        }
    };
    
    private static final DirectionID SIDE_DIRECTION = DirectionID.UP;

    
    @Override
    protected List<DirectionID> getStartingNodeDirections() {
        return STARTING_NODE_DIRECTIONS;
    }

    @Override
    protected DirectionID getSideDirection() {
        return SIDE_DIRECTION;
    }
    
}
