package drone.mock.direction;

import drone.mock.exception.NoAdjacentNode;
import drone.mock.Coord;

/**
 * Implementation of Direction as Interface/Implementation using the exploiting
 * the Polymorphism. 
 * Benefits: 
 * - This technique adheres to the Tell-Don't-Ask 
 *   principle: instead of asking an object about its state and then performing
 *   actions based on this, it is much easier to simply tell the object what it
 *   needs to do and let it decide for itself how to do that. 
 * - Removes duplicate code. You get rid of many almost identical conditionals. 
 * - If you need to add a new execution variant, all you need to do is add a 
 *   new subclass without touching the existing code (Open/Closed Principle).
 *
 * @author Fabrizio Faustinoni
 */
public interface IDirection {

    /**
     * Given a source's coordinates return the coordinates of the node adjacent
     * node in the specified direction.
     * @param sorceNodeCoord
     * @param matrixWidth
     * @param matrixHeight
     * @return
     * @throws NoAdjacentNode 
     */
    public Coord getAdjacentCoord(Coord sorceNodeCoord,
            int matrixWidth, int matrixHeight) throws NoAdjacentNode;

}
