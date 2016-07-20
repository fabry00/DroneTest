package drone.mock.direction;

import drone.mock.API.DirectionID;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.Coord;

/**
 * Represent the UP direction
 * @author Fabrizio Faustinoni
 */
public class UpDirection extends BaseDirection implements IDirection {

    private static final DirectionID LABEL = DirectionID.UP;

    /**
     * Given a source's coordinates return the coordinates of the adjacent node that 
     * is above.
     * @param sorceNodeCoord
     * @param matrixWidth
     * @param matrixHeight
     * @return
     * @throws NoAdjacentNode 
     */
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
