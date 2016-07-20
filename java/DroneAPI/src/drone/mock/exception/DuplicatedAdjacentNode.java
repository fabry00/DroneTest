package drone.mock.exception;

/**
 * Fired if there are two node with the same id/coords
 * 
 * @author Fabrizio Faustinoni
 */
public class DuplicatedAdjacentNode extends Exception{
    
    private static final long serialVersionUID = 1;
    
    public DuplicatedAdjacentNode(){
        super();
    }
    
    public DuplicatedAdjacentNode(String message) {
        super(message);
    }
    
    public DuplicatedAdjacentNode(String message,Throwable cause) {
        super(message, cause);
    }
}
