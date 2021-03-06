package com.id.droneapi.mock;

import java.util.Objects;
import com.id.droneapi.mock.api.IUrbanizationID;

/**
 * Unique Node ID object
 * @author Fabrizion Faustinoni
 */
public class UrbanizationID implements IUrbanizationID{
    
    private final String id;
    
    public UrbanizationID(String id) {
        this.id = id;
    }
    
    @Override
    public String toString(){
        return this.id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UrbanizationID other = (UrbanizationID) o;
        return Objects.equals(id, other.id) && Objects.equals(id, other.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    
}
