package drone.API.exception;

public class DirectionNotFound extends Exception{
    
    private static final long serialVersionUID = 1;
    
    public DirectionNotFound(){
        super();
    }
    
    public DirectionNotFound(String message) {
        super(message);
    }
    
    public DirectionNotFound(String message,Throwable cause) {
        super(message, cause);
    }
}
