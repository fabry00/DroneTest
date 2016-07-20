package com.id.droneapi.mock.api;

import com.id.droneapi.mock.exception.DirectionNotFound;
import com.id.droneapi.mock.exception.NoAdjacentNode;
import com.id.droneapi.mock.exception.NodeNotFound;

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
     * @param x coordinate X for the inputNode
     * @param y coordinate Y for the inputNode 
     * @return The node id
     * @throws com.id.droneapi.mock.exception.NodeNotFound The exception
     */
    public IUrbanizationID getUrbanizationID(double x, double y)
            throws NodeNotFound;

    /**
     * public getobtenerAdyacente(identificadorUrbanizaciónOrigen, dirección) =
     *                                                    identificadorAdyacente
     *
     * @param id source node id
     * @param diretcionID adjacent direction
     * @return the adjacent node
     * @throws NoAdjacentNode The exception
     * @throws com.id.droneapi.mock.exception.NodeNotFound The exception
     * @throws com.id.droneapi.mock.exception.DirectionNotFound The exception
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
     * @param id source node id
     * @param diretcionID the direction
     * @return the adjacent node id
     * @throws com.id.droneapi.mock.exception.NoAdjacentNode The exception
     * @throws com.id.droneapi.mock.exception.NodeNotFound The exception
     * @throws com.id.droneapi.mock.exception.DirectionNotFound The exception
     */
    public IUrbanizationID getAdjacent(IUrbanizationID id, DirectionID diretcionID)
            throws NoAdjacentNode, NodeNotFound,DirectionNotFound;
    
}
