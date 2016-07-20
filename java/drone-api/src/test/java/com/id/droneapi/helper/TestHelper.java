package com.id.droneapi.helper;

import com.id.droneapi.IDroneAPI;
import com.id.droneapi.INeighborhoodsAlgorithm;
import com.id.droneapi.impl.DroneAPI;
import com.id.droneapi.mock.API.IDealistaAPI;
import com.id.droneapi.mock.API.IUrbanizationID;
import com.id.droneapi.mock.Node;
import com.id.droneapi.mock.UrbanizationID;
import com.id.droneapi.mock.UrbanizationMatrix;
import com.id.droneapi.mock.exception.DuplicatedAdjacentNode;
import com.id.droneapi.mock.exception.NodeAlreadyAdded;
import com.id.droneapi.mock.exception.NodeAlreadyAddedAsAdjacent;
import com.id.droneapi.mock.exception.NodeNotFound;

import java.util.List;
import static junit.framework.Assert.assertEquals;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class TestHelper {

    public IDealistaAPI getIdealistaAPI(int col, int row) throws NodeAlreadyAddedAsAdjacent, DuplicatedAdjacentNode, NodeAlreadyAdded, NodeNotFound {
        IDealistaAPI idApi = createUBMatrix(row, col);
        return idApi;
    }

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

    public String testToString(IDealistaAPI api, TestCase test) {

        String string = "Test " + test.id + "\n";
        string += "InputNode (" + test.starting_node_x + "," + test.starting_node_y + ") range: " + test.range + "\n";
        string += api + "\n";

        return string;

    }
    
    public void executeTest(TestCase test, INeighborhoodsAlgorithm algorithm) throws Exception {
        IDealistaAPI api = getIdealistaAPI(test.ub_matrix_width, test.ub_matrix_height);
        IDroneAPI testDrone = new DroneAPI(api, algorithm);

        System.out.println(testToString(api, test));

        List<IUrbanizationID> actual
                = testDrone.getNeighborhoods(test.starting_node_x, test.starting_node_y, test.range);
        assertEquals(test.expected, actual);
    }
}
