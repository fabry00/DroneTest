package com.id.droneapi.mock.direction;

import com.id.droneapi.mock.API.DirectionID;
import com.id.droneapi.mock.exception.NoAdjacentNode;
import com.id.droneapi.mock.Coord;

/**
 * Represent the RIGHT direction
 * @author Fabrizio Faustinoni
 */
public class RightDirection extends BaseDirection implements IDirection {

    private static final DirectionID LABEL = DirectionID.RIGHT;

    /**
     * Given a source's coordinates return the coordinates of the adjacent node that 
     * on the rigth.
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
