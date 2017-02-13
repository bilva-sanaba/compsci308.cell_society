package cell.state;

import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * State constants for Game Of Life simulation
 * @author Mike Liu
 *
 */
public class GOLState extends CellState {
    
    public static final GOLState DEAD = new GOLState("Dead", Color.BLACK);
    public static final GOLState LIVE = new GOLState("Live", Color.WHITE);
    public static final List<GOLState> STATES = Arrays.asList(DEAD, LIVE);

    private GOLState(String state, Color color) {
        super(state, color);
    }

    @Override
    protected List<GOLState> getStates() {
        return STATES;
    }
}
