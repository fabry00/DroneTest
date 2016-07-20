package com.id.droneapi.impl.algorithm.v1.scandirection;


import com.id.droneapi.IScanDirection;
import com.id.droneapi.impl.algorithm.v1.Neighborhoods;
import com.id.droneapi.impl.algorithm.v1.Neighborhoods.NodeType;
import com.id.droneapi.impl.algorithm.v1.Node;
import com.id.droneapi.mock.API.DirectionID;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public interface IScanDirectionV1 extends  IScanDirection{
    
    
    public Entry<Node, DirectionID>
         getStartingNode(Map<Neighborhoods.NodeType, Node> parentVerticesNodes);

    public List<NodeType> getVertexOrdered();
}
