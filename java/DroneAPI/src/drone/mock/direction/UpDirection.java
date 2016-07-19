package drone.mock.direction;

import drone.API.DirectionID;
import drone.API.exception.NoAdjacentNode;
import drone.mock.Coord;

public class UpDirection extends BaseDirection implements IDirection {

    private static final DirectionID LABEL = DirectionID.UP;

    @Override
    public Coord getAdjacentCoord(Coord sorceNodeCoord,
            int matrixWidth,
            int matrixHeight) throws NoAdjacentNode {
        
        int y = (int) sorceNodeCoord.getY();
        if (y == 0) {
            throw new NoAdjacentNode();
        }
        return new Coord((int) sorceNodeCoord.getX(), y-1);
    }
    
    @Override
    protected DirectionID getDirectionID() {
        return LABEL;
    }
}
