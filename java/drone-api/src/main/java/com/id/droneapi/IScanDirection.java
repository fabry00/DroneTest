package com.id.droneapi;

import com.id.droneapi.mock.API.DirectionID;
import java.util.List;

/**
 *
 * @author Fabrizion Faustinoni
 */
public interface IScanDirection {
    
    public List<DirectionID> getOrderedDir();

    public DirectionID getNext(DirectionID direction);

    public int incrementIndex(int currentIndex, int size);

}
