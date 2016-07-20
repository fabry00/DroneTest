package drone.API;

import drone.mock.API.DirectionID;
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
