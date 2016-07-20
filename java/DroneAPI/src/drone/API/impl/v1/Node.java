package drone.API.impl.v1;

import java.util.Objects;
import drone.mock.API.DirectionID;
import drone.mock.API.IDealistaAPI;
import drone.mock.API.IUrbanizationID;
import drone.mock.exception.DirectionNotFound;
import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.exception.NodeNotFound;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Node {

    private static final Logger logger = Logger.getLogger(Node.class.getName());
    private final IUrbanizationID id;
    private final IDealistaAPI api;

    Node(IUrbanizationID id, IDealistaAPI api) {
        this.id = id;
        this.api = api;
    }

    public IUrbanizationID getId() {
        return this.id;
    }

    public Node getUpperLeft() throws NeighborhoodsAlgorithmEx {
        try {
            IUrbanizationID up = this.api.getAdjacent(this.id, DirectionID.UP);
            return new Node(this.api.getAdjacent(up, DirectionID.LEFT), this.api);
        } catch (NoAdjacentNode ex) {
            try {
                IUrbanizationID up = this.api.getAdjacent(this.id, DirectionID.UP);
                return new Node(up, this.api);

            } catch (NodeNotFound | DirectionNotFound ex1) {
                throw new NeighborhoodsAlgorithmEx("Oops. something went wrong.", ex1);
            } catch (NoAdjacentNode ex1) {
                // Not an error
            }
        } catch (DirectionNotFound ex) {
            throw new NeighborhoodsAlgorithmEx("Oops. something went wrong", ex);
        } catch (NodeNotFound ex) {
        }

        logger.log(Level.INFO, "UpperLeft vertex not found for node {0}", this.id);
        return null;
    }

    public Node getUpperRight() throws NeighborhoodsAlgorithmEx {
        try {
            IUrbanizationID up = this.api.getAdjacent(this.id, DirectionID.UP);
            return new Node(this.api.getAdjacent(up, DirectionID.RIGHT), this.api);
        } catch (NoAdjacentNode ex) {
            try {
                /*
                 this catch manage the following situation:
                 --> this.id = 01
                 01 02 03            
                 04 05 06
                 07 08 09
                
                 --> return 02

                 */
                IUrbanizationID right = this.api.getAdjacent(this.id, DirectionID.RIGHT);
                return new Node(right, this.api);

            } catch (DirectionNotFound ex1) {
                throw new NeighborhoodsAlgorithmEx("Oops. something went wrong", ex1);
            } catch (NodeNotFound | NoAdjacentNode ex1) {
                // Not an error
            }
        } catch (NodeNotFound | DirectionNotFound ex) {
            throw new NeighborhoodsAlgorithmEx("Oops. something went wrong", ex);
        }

        logger.log(Level.INFO, "UpperRight vertex not found for node {0}", this.id);
        return null;
    }

    public Node getBottomRight() throws NeighborhoodsAlgorithmEx {
        try {
            IUrbanizationID bottom = this.api.getAdjacent(this.id, DirectionID.DOWN);
            return new Node(this.api.getAdjacent(bottom, DirectionID.RIGHT), this.api);
        } catch (NoAdjacentNode ex) {
            try {
                /*
                 this catch manage the following situation:
                 --> this.id = 03
                 01 02 03            
                 04 05 06
                 07 08 09
                
                 --> return 06
                 */
                try {
                    IUrbanizationID down = this.api.getAdjacent(this.id, DirectionID.DOWN);
                    return new Node(down, this.api);
                } catch (NoAdjacentNode ex2) {
                    /*
                     this catch manage the following situation:
                     --> this.id = 07
                     01 02 03            
                     04 05 06
                     07 08 09
                
                     --> return 08
                     */
                    IUrbanizationID right = this.api.getAdjacent(this.id, DirectionID.RIGHT);
                    return new Node(right, this.api);
                }
            } catch (DirectionNotFound ex1) {
                throw new NeighborhoodsAlgorithmEx("Oops. something went wrong", ex1);
            } catch (NoAdjacentNode | NodeNotFound ex1) {
                // Not an error
            }
        } catch (DirectionNotFound ex) {
            throw new NeighborhoodsAlgorithmEx("Oops. something went wrong", ex);
        } catch (NodeNotFound ex2) {
            // Not an error
        }

        logger.log(Level.INFO, "BottomRight vertex not found for node {0}", this.id);
        return null;
    }

    public Node getBottomLeft() throws NeighborhoodsAlgorithmEx {
        try {
            IUrbanizationID bottom = this.api.getAdjacent(this.id, DirectionID.DOWN);
            return new Node(this.api.getAdjacent(bottom, DirectionID.LEFT), this.api);
        } catch (NoAdjacentNode ex) {
            try {
                /*
                 this catch manage the following situation:
                 --> this.id = 01
                 01 02 03            
                 04 05 06
                 07 08 09
                
                 --> return 04
                 */
                IUrbanizationID down = this.api.getAdjacent(this.id, DirectionID.DOWN);
                return new Node(down, this.api);

            } catch (NoAdjacentNode ex1) {
                try {
                    /*
                     this catch manage the following situation:
                     --> this.id = 09
                     01 02 03            
                     04 05 06
                     07 08 09

                     --> return 08
                     */
                    IUrbanizationID left = this.api.getAdjacent(this.id, DirectionID.LEFT);
                    return new Node(left, this.api);
                } catch (NodeNotFound | DirectionNotFound ex2) {
                    throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
                } catch (NoAdjacentNode ex2) {
                    // Not an error
                }
            } catch (NodeNotFound | DirectionNotFound ex1) {
                throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
            }
        } catch (DirectionNotFound ex) {
            throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
        } catch (NodeNotFound ex2) {
            // Not an error
        }

        logger.log(Level.INFO, "BottomLeft vertex not found for node {0}", this.id);
        return null;
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
