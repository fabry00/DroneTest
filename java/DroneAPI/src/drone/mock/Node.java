package drone.mock;

import java.util.Objects;
import drone.API.IUrbanizationID;
import drone.API.exception.NodeAlreadyAddedAsAdjacent;

public class Node {

    private final IUrbanizationID id;
    private Coord coords;

    // To create the instance use the Builder
    private Node(IUrbanizationID id) {
        this.id = id;
    }

    public IUrbanizationID getId() {
        return id;
    }

    public Coord getCoord() {
        return this.coords;
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
        return Objects.equals(id, other.id)
                && Objects.equals(coords, other.coords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coords);
    }
    
    @Override
    public String toString() {
        return this.id.toString()+this.coords.toString();
    }

    private void setCoords(Coord coords) {
        this.coords = coords;
    }

    public static class Builder {

        private Coord coords;

        public Builder withCoords(double x, double y) throws NodeAlreadyAddedAsAdjacent {
            coords = new Coord(x, y);
            return this;
        }

        public Node build(IUrbanizationID id) {
            Node node = new Node(id);
            node.setCoords(coords);
            return node;
        }

    }
}
