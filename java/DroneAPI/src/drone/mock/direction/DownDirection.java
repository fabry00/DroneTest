package drone.mock.direction;

import drone.mock.API.DirectionID;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.Coord;

/**
 * Represent the DOWN direction
 * @author Fabrizio Faustinoni
 */
public class DownDirection extends BaseDirection implements IDirection {

    private static final DirectionID LABEL = DirectionID.DOWN;

    /**
     * Given a source's coordinates return the coordinates of the adjacent 
     * node that is below.
     * @param sorceNodeCoord
     * @param matrixWidth
     * @param matrixHeight
     * @return
     * @throws NoAdjacentNode 
     */
    @Override
    public Coord getAdjacentCoord(Coord sorceNodeCoord,
            int matrixWidth, int matrixHeight) throws NoAdjacentNode {

        int y = (int) sorceNodeCoord.getY();
        // Cords are 0 based
        if (y == matrixHeight-1) {
            throw new NoAdjacentNode();
        }
        return new Coord((int) sorceNodeCoord.getX(), y + 1);
    }

    @Override
    protected DirectionID getDirectionID() {
        return LABEL;
    }
}
