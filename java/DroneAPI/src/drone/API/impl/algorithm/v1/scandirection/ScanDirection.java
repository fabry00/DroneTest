package drone.API.impl.algorithm.v1.scandirection;

import drone.API.impl.algorithm.v1.Neighborhoods;
import drone.API.impl.algorithm.v1.Neighborhoods.NodeType;
import drone.API.impl.algorithm.v1.Node;
import java.util.List;
import drone.mock.API.DirectionID;
import java.util.Map;

public enum ScanDirection {

    CLOCKWISE(new Clockwise()),
    CONUTERCLOCKWISE(new CounterClockWise());

    private final IScanDirection implementation;
    
    private ScanDirection(IScanDirection implementation) {
        this.implementation = implementation;
    }

    public int getSizeSequence() {
        return implementation.getOrderedDir().size();
    }
    
    public List<DirectionID> getList(){
        return implementation.getOrderedDir();
    }
    
    public List<NodeType> getVertexOrdered() {
        return implementation.getVertexOrdered();
    }
    public DirectionID getNext(DirectionID direction) {
        return implementation.getNext(direction);
        
    }

    public int incrementIndex(int currentIndex, int size) {
        return implementation.incrementIndex(currentIndex, size);
    }

    public Map.Entry<Node, DirectionID> getStartingNode(Map<Neighborhoods.NodeType, Node> parentVerticesNodes) {
        return implementation.getStartingNode(parentVerticesNodes);
    }
};
