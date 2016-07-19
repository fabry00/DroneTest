package drone.mock;

import java.util.Objects;

public class Coord {

    private final double x;
    private final double y;

    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Coord other = (Coord) o;
        return Objects.equals(x, other.x) && Objects.equals(y, other.y);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
    
    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
