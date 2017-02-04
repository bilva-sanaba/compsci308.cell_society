package cell;

import javafx.scene.paint.Color;

public class CellState {
    
    private int myState;
    private Color myColor;
    
    public CellState(int state, Color color) {
        myState = state;
        myColor = color;
    }
    
    public Color getColor() {
        return myColor;
    }
    
    public boolean equals(int state) {
        return state == myState;
    }
    
    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof CellState)) {
            return false;
        }
        return ((CellState)other).myState == myState;
    }
}
