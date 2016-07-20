package com.id.droneapi;

import com.id.droneapi.mock.api.DirectionID;
import java.util.List;

/**
 *
 * @author Fabrizion Faustinoni
 */
public interface IScanDirection {
    
    /**
     * Return the list of the directions ordered
     * @return the list 
     */
    public List<DirectionID> getOrderedDir();

    /**
     * the next direction
     * @param direction current direction
     * @return  The next direction
     */
    public DirectionID getNext(DirectionID direction);

    /**
     * increment an integer following the direction
     * @param currentIndex index to increment
     * @param size max number
     * @return the index incremented
     */
    public int incrementIndex(int currentIndex, int size);

}
