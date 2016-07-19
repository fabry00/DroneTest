package drone.API.exception;

public class NeighborhoodsAlgorithmEx extends Exception{
    
    private static final long serialVersionUID = 1;
    
    public NeighborhoodsAlgorithmEx(){
        super();
    }
    
    public NeighborhoodsAlgorithmEx(String message) {
        super(message);
    }
    
    public NeighborhoodsAlgorithmEx(String message,Throwable cause) {
        super(message, cause);
    }
}
