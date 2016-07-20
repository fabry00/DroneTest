package com.id.droneapi.impl.algorithm.v2;

import java.util.Objects;
import com.id.droneapi.mock.api.IUrbanizationID;

public class Node {

    private final IUrbanizationID id;
    
    public Node(IUrbanizationID id) {
        this.id = id;
    }

    public IUrbanizationID getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Node other = (Node) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
