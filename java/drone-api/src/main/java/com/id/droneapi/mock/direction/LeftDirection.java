package com.id.droneapi.mock.direction;

import com.id.droneapi.mock.api.DirectionID;
import com.id.droneapi.mock.exception.NoAdjacentNode;
import com.id.droneapi.mock.Coord;

/**
 * Represent the LEFT direction
 * @author Fabrizio Faustinoni
 */
public class LeftDirection extends BaseDirection implements IDirection {

    private static final DirectionID LABEL = DirectionID.LEFT;

    /**
     * Given a source's coordinates return the coordinates of the node adjacent
     * node in the specified direction.
     * @param sorceNodeCoord the source node coordinates
     * @param matrixWidth matrix width
     * @param matrixHeight matrix height
     * @return the Cordinates
     * @throws NoAdjacentNode The exception 
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
