package drone.mock.API;

import drone.mock.exception.DirectionNotFound;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.exception.NodeNotFound;

/**
 * This interaface represent the functions that IDealista provides.
 * 
 * @author Fabrizio Faustinoni
 */
public interface IDealistaAPI {

    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    };

    /**
     * 
     * obtenerIdentificadorUrbanización(coordenadaX, coordenadaY) =
     *                                                 identificadorUrbanización
     *
     * @param x
     * @param y
     * @return
     * @throws drone.mock.exception.NodeNotFound
     */
    public IUrbanizationID getUrbanizationID(double x, double y)
            throws NodeNotFound;

    /**
     * public getobtenerAdyacente(identificadorUrbanizaciónOrigen, dirección) =
     *                                                    identificadorAdyacente
     *
     * @param id
     * @param diretcionID
     * @return
     * @throws NoAdjacentNode
     * @throws drone.mock.exception.NodeNotFound
     * @throws drone.mock.exception.DirectionNotFound
     */
    public IUrbanizationID getAdjacent(String id, String diretcionID)
            throws NoAdjacentNode, NodeNotFound, DirectionNotFound;

    /**
     * public getobtenerAdyacente(identificadorUrbanizaciónOrigen, dirección) =
     *                                                    identificadorAdyacente
     *
     * Overloaded method using IUrbanizationID and DirectionID class instead of
     * String
     *
     * @param id
     * @param diretcionID
     * @return
     * @throws drone.mock.exception.NoAdjacentNode
     * @throws drone.mock.exception.NodeNotFound
     * @throws drone.mock.exception.DirectionNotFound
     */
    public IUrbanizationID getAdjacent(IUrbanizationID id, DirectionID diretcionID)
            throws NoAdjacentNode, NodeNotFound,DirectionNotFound;
    
}
