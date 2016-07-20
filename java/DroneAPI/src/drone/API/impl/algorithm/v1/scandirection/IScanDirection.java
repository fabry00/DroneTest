package drone.API.impl.algorithm.v1.scandirection;

import drone.API.impl.algorithm.v1.Neighborhoods;
import drone.API.impl.algorithm.v1.Neighborhoods.NodeType;
import drone.API.impl.algorithm.v1.Node;
import drone.mock.API.DirectionID;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Fabrizion Faustinoni
 */
public interface IScanDirection {
    
    public List<DirectionID> getOrderedDir();

    public DirectionID getNext(DirectionID direction);

    public int incrementIndex(int currentIndex, int size);

    public Entry<Node, DirectionID>
         getStartingNode(Map<Neighborhoods.NodeType, Node> parentVerticesNodes);

    public List<NodeType> getVertexOrdered();
}
