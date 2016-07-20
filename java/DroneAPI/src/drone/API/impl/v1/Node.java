package drone.API.impl.v1;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import drone.mock.API.DirectionID;
import drone.mock.API.IDealistaAPI;
import drone.mock.API.IUrbanizationID;
import drone.mock.exception.DirectionNotFound;
import drone.API.exception.NeighborhoodsAlgorithmEx;
import drone.mock.exception.NoAdjacentNode;
import drone.mock.exception.NodeNotFound;

public class Node {

    private final IUrbanizationID id;
    private final IDealistaAPI api;

    Node(IUrbanizationID id, IDealistaAPI api) {
        this.id = id;
        this.api = api;
    }

    public IUrbanizationID getId() {
        return this.id;
    }

    public Node getUpperLeft() throws NoAdjacentNode, NeighborhoodsAlgorithmEx {
        try {
            IUrbanizationID up = this.api.getAdjacent(this.id, DirectionID.UP);
            return new Node(this.api.getAdjacent(up, DirectionID.LEFT), this.api);
        } catch (NoAdjacentNode ex) {
            try {
                IUrbanizationID up = this.api.getAdjacent(this.id, DirectionID.UP);
                return new Node(up, this.api);

            } catch (NodeNotFound | DirectionNotFound ex1) {
                throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
            }
        } catch (DirectionNotFound ex) {
            throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
        } catch (NodeNotFound ex) {
            throw new NoAdjacentNode();
        }
    }

    public Node getUpperRight() throws NoAdjacentNode, NeighborhoodsAlgorithmEx {
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
                throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
            } catch (NodeNotFound ex1) {
                throw new NoAdjacentNode();
            }
        } catch (NodeNotFound | DirectionNotFound ex) {
            throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
        }
    }

    public Node getBottomRight() throws NoAdjacentNode, NeighborhoodsAlgorithmEx {
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
                throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
            }
            catch (NodeNotFound ex1){
                throw new NoAdjacentNode();
            }
        } catch (DirectionNotFound ex) {
            throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
        }catch (NodeNotFound ex2 ) {
            throw new NoAdjacentNode();
        }

    }

    public Node getBottomLeft() throws NoAdjacentNode, NeighborhoodsAlgorithmEx {
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
                }
            } catch (NodeNotFound | DirectionNotFound ex1) {
                throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
            }
        } catch (DirectionNotFound ex) {
            throw new NeighborhoodsAlgorithmEx("Oops. something went wrong");
        }catch(NodeNotFound ex2){
            throw new NoAdjacentNode();
        }

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
