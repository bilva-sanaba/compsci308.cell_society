package cell.state;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * The state of a cell
 * @author Mike Liu
 *
 */
public abstract class CellState {
    
    private String myState;
    private Color myColor;
    
    public CellState(String state, Color color) {
        myState = state;
        myColor = color;
    }
    
    @Override
    public String toString() {
        return myState;
    }
    
    public Color getColor() {
        return myColor;
    }
    
    public boolean equals(String state) {
        return state.equals(myState);
    }
    
    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof CellState)) {
            return false;
        }
        return equals(((CellState)other).myState);
    }
    
    /**
     * Returns a new state
     * Deterministic behavior
     * @return
     */
    public CellState rotate() {
        List<? extends CellState> states = getStates();
        int len = states.size();
        for(int i = 0; i < len; i++) {
            if(this.equals(states.get(i))) {
                return states.get((i+1) % len); 
            }
        }
        return states.get(0);
    }
    
    protected abstract List<? extends CellState> getStates();
    
    public abstract int toInt();
}
