package drone.API.impl.algorithm.v1.vertex;

import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.API.impl.algorithm.v1.Node;
import drone.mock.API.IDealistaAPI;
import drone.mock.API.IUrbanizationID;

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
