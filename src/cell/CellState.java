package cell;

import javafx.scene.paint.Color;

public class CellState {
    
    private String myState;
    private Color myColor;
    
    public CellState(String state, Color color) {
        myState = state;
        myColor = color;
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
        return ((CellState)other).myState == myState;
    }
}
