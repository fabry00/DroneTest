package drone.API.exception;

public class NodeNotFound extends Exception{
    
    private static final long serialVersionUID = 1;
    
    public NodeNotFound(){
        super();
    }
    
    public NodeNotFound(String message) {
        super(message);
    }
    
    public NodeNotFound(String message,Throwable cause) {
        super(message, cause);
    }
}
