package com.id.droneapi.mock.direction;

import com.id.droneapi.mock.api.DirectionID;
import com.id.droneapi.mock.exception.NoAdjacentNode;
import com.id.droneapi.mock.Coord;

/**
 * Represent the DOWN direction
 * @author Fabrizio Faustinoni
 */
public class DownDirection extends BaseDirection implements IDirection {

    private static final DirectionID LABEL = DirectionID.DOWN;

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
