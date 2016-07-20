package drone.API.impl.algorithm.v2.sides;

import drone.mock.API.DirectionID;
import java.util.ArrayList;
import java.util.List;

public class UpSide extends Side implements ISide {

    private static final List<DirectionID> STARTING_NODE_DIRECTIONS = new ArrayList<DirectionID>() {
        {
            add(DirectionID.UP);
            add(DirectionID.LEFT);
        }
    };
    
    private static final DirectionID SIDE_DIRECTION = DirectionID.RIGHT;
    
    @Override
    protected List<DirectionID> getStartingNodeDirections() {
        return STARTING_NODE_DIRECTIONS;
    }

    @Override
    protected DirectionID getSideDirection() {
        return SIDE_DIRECTION;
    }
    
}
