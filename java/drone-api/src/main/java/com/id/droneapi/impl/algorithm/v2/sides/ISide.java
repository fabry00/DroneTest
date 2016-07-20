package com.id.droneapi.impl.algorithm.v2.sides;

import com.id.droneapi.impl.algorithm.v2.Node;
import com.id.droneapi.mock.API.IDealistaAPI;
import com.id.droneapi.mock.API.IUrbanizationID;
import java.util.Collection;

public interface ISide {
    
    public enum SideType {

        UP, RIGHT, BOTTOM, LEFT
    };

    public Collection<IUrbanizationID> getNodesIds();

    public void calculateNeighborhoodsNodes(IDealistaAPI api, IUrbanizationID centralNode, int range);

    public Collection<Node> getNodes();

    public void setIsFirst(boolean isFirstSide);
    
     public void setType(SideType sideType);
     
      public SideType getSideType();
    
}
