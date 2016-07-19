package drone.API.exception;

public class NodeAlreadyAddedAsAdjacent extends Exception{
    
    private static final long serialVersionUID = 1;
    
    public NodeAlreadyAddedAsAdjacent(){
        super();
    }
    
    public NodeAlreadyAddedAsAdjacent(String message) {
        super(message);
    }
    
    public NodeAlreadyAddedAsAdjacent(String message,Throwable cause) {
        super(message, cause);
    }
}
