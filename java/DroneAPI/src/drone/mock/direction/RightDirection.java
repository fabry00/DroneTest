package drone.mock.direction;

import drone.API.DirectionID;
import drone.API.exception.NoAdjacentNode;
import drone.mock.Coord;

public class RightDirection extends BaseDirection implements IDirection {

    private static final DirectionID LABEL = DirectionID.RIGHT;

    @Override
    public Coord getAdjacentCoord(Coord sorceNodeCoord,
            int matrixWidth, int matrixHeight) throws NoAdjacentNode {

        int x = (int) sorceNodeCoord.getX();
        // Cords are 0 based
        if (x == matrixWidth-1) {
            throw new NoAdjacentNode();
        }
        return new Coord((int) x + 1, sorceNodeCoord.getY());
    }

    @Override
    protected DirectionID getDirectionID() {
        return LABEL;
    }
}
