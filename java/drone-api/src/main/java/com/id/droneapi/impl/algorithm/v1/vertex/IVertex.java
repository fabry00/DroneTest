package com.id.droneapi.impl.algorithm.v1.vertex;

import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;
import com.id.droneapi.impl.algorithm.v1.Node;
import com.id.droneapi.mock.api.IDealistaAPI;
import com.id.droneapi.mock.api.IUrbanizationID;

/**
 * Represent the Vertex interface
 * @author Fabrizio Faustinoni
 */
public interface IVertex {
    
    /**
     * Calculate the Vertex Node starting form the sourceNode
     * @param sourceNode the source node
     * @param api the Idealista API
     * @return the Node
     * @throws NeighborhoodsAlgorithmEx the exception
     */
     public Node getNode(IUrbanizationID sourceNode, IDealistaAPI api)
            throws NeighborhoodsAlgorithmEx;
}
