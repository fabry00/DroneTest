package com.id.droneapi.mock.direction;

import com.id.droneapi.mock.API.DirectionID;

/**
 * Base class that implements the IDirection interface
 * @author Fabrizio Faustinoni
 */
public abstract class BaseDirection implements IDirection {

    /**
     * Return the id of the Direction (RIGHT-LEFT-UP-DOWN)
     * @return 
     */
    protected abstract DirectionID getDirectionID();

}
