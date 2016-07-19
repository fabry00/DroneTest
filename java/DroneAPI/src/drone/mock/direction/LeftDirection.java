package drone.mock.direction;

import drone.API.DirectionID;
import drone.API.exception.NoAdjacentNode;
import drone.mock.Coord;

public class LeftDirection extends BaseDirection implements IDirection {

    private static final DirectionID LABEL = DirectionID.LEFT;

    @Override
    public Coord getAdjacentCoord(Coord sorceNodeCoord,
            int matrixWidth, int matrixHeight) throws NoAdjacentNode {
        
        int x = (int) sorceNodeCoord.getX();
        if (x == 0) {
            throw new NoAdjacentNode();
        }
        return new Coord(x - 1, (int) sorceNodeCoord.getY());
    }

    @Override
    protected DirectionID getDirectionID() {
        return LABEL;
    }
}
