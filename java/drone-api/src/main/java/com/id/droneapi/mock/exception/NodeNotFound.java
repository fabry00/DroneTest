package com.id.droneapi.mock.exception;

/**
 * Fired if the node required is not found
 * @author Fabrizio Faustinoni
 */
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
