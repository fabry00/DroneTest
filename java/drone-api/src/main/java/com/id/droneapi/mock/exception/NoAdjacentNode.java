package com.id.droneapi.mock.exception;

/**
 * Exception fired if the required Adjacent not is not found
 * 
 * @author Fabrizio Faustinoni
 */
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
