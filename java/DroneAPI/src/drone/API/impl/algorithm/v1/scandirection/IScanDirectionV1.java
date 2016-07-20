package drone.API.impl.algorithm.v1.scandirection;


import drone.API.IScanDirection;
import drone.API.impl.algorithm.v1.Neighborhoods;
import drone.API.impl.algorithm.v1.Neighborhoods.NodeType;
import drone.API.impl.algorithm.v1.Node;
import drone.mock.API.DirectionID;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public interface IScanDirectionV1 extends  IScanDirection{
    
    
    public Entry<Node, DirectionID>
         getStartingNode(Map<Neighborhoods.NodeType, Node> parentVerticesNodes);

    public List<NodeType> getVertexOrdered();
}
