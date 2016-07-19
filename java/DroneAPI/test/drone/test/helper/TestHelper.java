package drone.test.helper;

import drone.API.IDealistaAPI;
import drone.API.exception.DuplicatedAdjacentNode;
import drone.API.exception.NodeAlreadyAdded;
import drone.API.exception.NodeAlreadyAddedAsAdjacent;
import drone.API.exception.NodeNotFound;
import drone.mock.Node;
import drone.mock.UrbanizationID;
import drone.mock.UrbanizationMatrix;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class TestHelper {
    
    
    public IDealistaAPI getIdealistaAPI(int col,int row) throws NodeAlreadyAddedAsAdjacent, DuplicatedAdjacentNode, NodeAlreadyAdded, NodeNotFound {
        IDealistaAPI idApi = createUBMatrix(row,col);
        return idApi;
    }
    
    /**
     * @param row
     * @param col
     * @return 
     * @throws drone.API.exception.NodeAlreadyAddedAsAdjacent
     * @throws drone.API.exception.DuplicatedAdjacentNode
     * @throws drone.API.exception.NodeAlreadyAdded
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
