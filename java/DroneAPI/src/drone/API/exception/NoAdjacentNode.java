package drone.API.exception;

public class NoAdjacentNode extends Exception{
    
    private static final long serialVersionUID = 1;
    
    public NoAdjacentNode(){
        super();
    }
    
    public NoAdjacentNode(String message) {
        super(message);
    }
    
    public NoAdjacentNode(String message,Throwable cause) {
        super(message, cause);
    }
}
