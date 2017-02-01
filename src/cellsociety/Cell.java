package cellsociety;

import javafx.scene.paint.Color;

/**
 * Superclass for individual cell for the grid
 * Keeps track of state and color
 * @author Mike Liu
 *
 */
public abstract class Cell {
    
    private int state;
    private Color color;
    
    public Cell() {
        this(0, Color.WHITE);
    }
    
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
