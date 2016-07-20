package drone.API.impl.algorithm.v1;

import java.util.Objects;
import drone.mock.API.IUrbanizationID;

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
