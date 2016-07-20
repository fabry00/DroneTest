package drone.API.impl.algorithm.v2.sides;

import drone.API.impl.algorithm.v2.Node;
import drone.mock.API.IDealistaAPI;
import drone.mock.API.IUrbanizationID;
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
