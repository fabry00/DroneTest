package drone.mock.direction;

import drone.API.DirectionID;

public abstract class BaseDirection implements IDirection {

    protected abstract DirectionID getDirectionID();

}
