package drone.mock.direction;

import drone.mock.API.DirectionID;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.Coord;

/**
 * Represent the LEFT direction
 * @author Fabrizio Faustinoni
 */
public class LeftDirection extends BaseDirection implements IDirection {

    private static final DirectionID LABEL = DirectionID.LEFT;

    /**
     * Given a source's coordinates return the coordinates of the adjacent node that 
     * on the left.
     * @param sorceNodeCoord
     * @param matrixWidth
     * @param matrixHeight
     * @return
     * @throws NoAdjacentNode 
     */
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
