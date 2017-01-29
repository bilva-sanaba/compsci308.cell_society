package cellsociety;

import javafx.scene.paint.Color;

/**
 * 
 * @author Mike Liu
 *
 */
public class Cell {
    
    private int state;
    private Color color;
    
    public Cell(int state, Color color) {
        this.state = state;
        this.color = color;
    }
    
    public int getState() {
        return state;
    }
    
    public Color getColor() {
        return color;
    }
}
