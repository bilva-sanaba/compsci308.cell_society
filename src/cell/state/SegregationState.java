package cell.state;

import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * State constants for Segregation simulation
 * @author Mike Liu
 * @author Bilva Sanaba
 */
public class SegregationState extends CellState {
    
    public static final SegregationState EMPTY = new SegregationState("Empty", Color.WHITE);
    public static final SegregationState RED = new SegregationState("Red", Color.RED);
    public static final SegregationState BLUE = new SegregationState("Blue", Color.BLUE);
    public static final List<SegregationState> STATES = Arrays.asList(EMPTY, RED, BLUE);

    private SegregationState(String state, Color color) {
        super(state, color);
    }

    @Override
    protected List<SegregationState> getStates() {
        return STATES;
    }

    @Override
    public int toInt() {
        for(int i = 0; i < STATES.size(); i++) {
            if(this.equals(STATES.get(i))) {
                return i;
            }
        }
        return 0;
    }
}
