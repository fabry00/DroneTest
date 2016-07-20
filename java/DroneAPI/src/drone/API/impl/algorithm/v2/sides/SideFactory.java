package drone.API.impl.algorithm.v2.sides;

import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.API.impl.algorithm.v2.sides.ISide.SideType;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create the right instance associated to the right SideType
 *
 * @author Fabrizio Faustinoni
 */
public class SideFactory {

    /**
     * Map that associate for each DirectionID the right implementation
     */
    private static final Map<SideType, Class<ISide>> FACTORY_MAP
            = new HashMap() {
                {
                    put(SideType.UP, UpSide.class);
                    put(SideType.RIGHT, RightSide.class);
                    put(SideType.BOTTOM, BottomSide.class);
                    put(SideType.LEFT, LeftSide.class);
                }
            };

    /**
     * @param type
     * @param isFirstSide
     * @return 
     * @throws drone.API.exception.NeighborhoodsAlgorithmEx 
     */
    public ISide createDirection(SideType type, boolean isFirstSide) throws NeighborhoodsAlgorithmEx {

        Class<ISide> _class = FACTORY_MAP.get(type);
        try {
            ISide side =  _class.newInstance();
            
            side.setIsFirst(isFirstSide);
            side.setType(type);
            return side;
        } catch (InstantiationException | IllegalAccessException |
                SecurityException | IllegalArgumentException ex) {

            Logger.getLogger(SideFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new NeighborhoodsAlgorithmEx();

    }
}
