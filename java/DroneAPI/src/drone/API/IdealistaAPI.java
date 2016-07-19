package drone.API;

import drone.API.exception.DirectionNotFound;
import drone.API.exception.NoAdjacentNode;
import drone.API.exception.NodeNotFound;

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
     * @throws drone.API.exception.NodeNotFound
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
     * @throws drone.API.exception.NodeNotFound
     * @throws drone.API.exception.DirectionNotFound
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
     * @throws drone.API.exception.NoAdjacentNode
     * @throws drone.API.exception.NodeNotFound
     * @throws drone.API.exception.DirectionNotFound
     */
    public IUrbanizationID getAdjacent(IUrbanizationID id, DirectionID diretcionID)
            throws NoAdjacentNode, NodeNotFound,DirectionNotFound;
    
    

}
