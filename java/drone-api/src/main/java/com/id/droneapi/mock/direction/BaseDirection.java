package com.id.droneapi.mock.direction;

import com.id.droneapi.mock.api.DirectionID;

/**
 * Base class that implements the IDirection interface
 * @author Fabrizio Faustinoni
 */
public abstract class BaseDirection implements IDirection {

    /**
     * Return the id of the Direction (RIGHT-LEFT-UP-DOWN)
     * @return the direction id
     */
    protected abstract DirectionID getDirectionID();

}
