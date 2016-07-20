package drone.mock.direction;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import drone.mock.API.DirectionID;
import drone.mock.exception.DirectionNotFound;

/**
 * Create the righ instance associated to the right Direction id
 *
 * @author Fabrizio Faustinoni
 */
public class DirectionFactory {

    /**
     * Map that associate for each DirectionID the right implementation
     */
    private static final Map<DirectionID, Class<IDirection>> FACTORY_MAP
            = new HashMap() {
        {
            put(DirectionID.UP, UpDirection.class);
            put(DirectionID.RIGHT, RightDirection.class);
            put(DirectionID.DOWN, DownDirection.class);
            put(DirectionID.LEFT, LeftDirection.class);
        }
    };

    /**
     * Create an instance of the specific IDirection
     * @param directionID
     * @param rows Matrix rows
     * @param col Matrix columns
     * @return
     * @throws DirectionNotFound 
     */
    public IDirection createDirection(DirectionID directionID, int rows, int col)
            throws DirectionNotFound {

        Class<IDirection> _class = FACTORY_MAP.get(directionID);
        try {
            return _class.newInstance();
        } catch (InstantiationException | IllegalAccessException |
                SecurityException | IllegalArgumentException ex) {
            
            Logger.getLogger(DirectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new DirectionNotFound();

    }
}
