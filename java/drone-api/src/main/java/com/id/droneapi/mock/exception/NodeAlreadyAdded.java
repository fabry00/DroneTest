package com.id.droneapi.mock.exception;

/**
 * Fired if a same node already exists in the UrmanizationMatrix
 * @author Fabrizio Faustinoni
 */
public class NodeAlreadyAdded extends Exception{
    
    private static final long serialVersionUID = 1;
    
    public NodeAlreadyAdded(){
        super();
    }
    
    public NodeAlreadyAdded(String message) {
        super(message);
    }
    
    public NodeAlreadyAdded(String message,Throwable cause) {
        super(message, cause);
    }
}
