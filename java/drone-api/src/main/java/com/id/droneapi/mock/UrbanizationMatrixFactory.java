package com.id.droneapi.mock;

import com.id.droneapi.mock.api.IDealistaAPI;
import com.id.droneapi.mock.exception.DuplicatedAdjacentNode;
import com.id.droneapi.mock.exception.NodeAlreadyAdded;
import com.id.droneapi.mock.exception.NodeAlreadyAddedAsAdjacent;

/**
 * Create the Urbanization Matrix
 *
 * @author Fabrizio Faustinoni
 */
public class UrbanizationMatrixFactory {

    /**
     * @param row
     * @param col
     * @return
     * @throws com.id.droneapi.mock.exception.NodeAlreadyAddedAsAdjacent
     * @throws com.id.droneapi.mock.exception.DuplicatedAdjacentNode
     * @throws com.id.droneapi.mock.exception.NodeAlreadyAdded
     */
    public IDealistaAPI createUBMatrix(int row, int col) throws NodeAlreadyAddedAsAdjacent, DuplicatedAdjacentNode, NodeAlreadyAdded {
        UrbanizationMatrix.Builder builder = new UrbanizationMatrix.Builder();

        int id = 1;
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                builder.addNode(new Node.Builder()
                        .withCoords((double) x, (double) y)
                        .build(getId(id++)));
            }
        }

        UrbanizationMatrix g = builder.build(row, col);
        // Print the Matrix
        //System.out.println(g.toString());

        IDealistaAPI api = (IDealistaAPI) g;

        return api;
    }
    
    public UrbanizationID getId(int id) {
        return new UrbanizationID((id < 10) ? "0" + id : id + "");
    }
}
