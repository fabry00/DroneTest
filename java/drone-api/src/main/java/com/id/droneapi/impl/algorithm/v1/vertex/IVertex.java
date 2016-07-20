package com.id.droneapi.impl.algorithm.v1.vertex;

import com.id.droneapi.exception.NeighborhoodsAlgorithmEx;
import com.id.droneapi.impl.algorithm.v1.Node;
import com.id.droneapi.mock.API.IDealistaAPI;
import com.id.droneapi.mock.API.IUrbanizationID;

/**
 * Represent the Vertex interface
 * @author Fabrizio Faustinoni
 */
public interface IVertex {
    /**
     * Calculate the Vertex Node starting form the sourceNode
     * @param sourceNode
     * @param api
     * @return
     * @throws NeighborhoodsAlgorithmEx 
     */
     public Node getNode(IUrbanizationID sourceNode, IDealistaAPI api)
            throws NeighborhoodsAlgorithmEx;
}
