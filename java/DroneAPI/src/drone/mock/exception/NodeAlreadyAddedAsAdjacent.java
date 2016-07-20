package drone.mock.exception;

/**
 * Fired if the adjacent node has been already added to the UrbanizationMatrix
 * @author Fabrizio Faustinoni
 */
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
