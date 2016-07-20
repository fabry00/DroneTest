package drone.API.impl.algorithm.v2.sides;

import drone.API.impl.algorithm.v2.Node;
import drone.mock.API.IDealistaAPI;
import drone.mock.API.IUrbanizationID;
import java.util.Collection;

public interface ISide {

    public Collection<IUrbanizationID> getNodesIds();

    public void calculateNeighborhoodsNodes(IDealistaAPI api, IUrbanizationID centralNode, int range);

    public Collection<Node> getNodes();
    
}
