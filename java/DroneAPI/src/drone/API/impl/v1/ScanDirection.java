package drone.API.impl.v1;

import java.util.ArrayList;
import java.util.List;
import drone.API.DirectionID;

public enum ScanDirection {

    CLOCKWISE(new ArrayList<DirectionID>() {
        {
            add(DirectionID.RIGHT);
            add(DirectionID.DOWN);
            add(DirectionID.LEFT);
            add(DirectionID.UP);
        }
    }),
    CONUTERCLOCKWIZE(new ArrayList<DirectionID>() {
        {
            add(DirectionID.LEFT);
            add(DirectionID.DOWN);
            add(DirectionID.RIGHT);
            add(DirectionID.UP);
        }
    });

    private final List<DirectionID> sequence;

    private ScanDirection(List<DirectionID> sequence) {
        this.sequence = sequence;
    }

    public int getSizeSequence() {
        return this.sequence.size();
    }
    
    public List<DirectionID> getList(){
        return this.sequence;
    }
    
    public DirectionID getNext(DirectionID direction) {

        int index = 0;
        for (DirectionID dir : sequence) {
            if (direction.equals(dir)) {
                break;
            }
            index++;
        }

        if (this.equals(CLOCKWISE)) {
            return index == sequence.size()-1 ? sequence.get(0) : sequence.get(index + 1);
        } else {
            return index == 0 ? sequence.get(sequence.size() - 1) : sequence.get(index - 1);
        }
    }
};
