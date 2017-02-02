package cellsociety;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import javafx.scene.paint.Color;

/**
 * Superclass for individual cell in the grid
 * Keeps track of state and color
 * @author Mike Liu
 *
 */
public abstract class Cell {
    
    private int state;
    private Color color;
    private Collection<? extends Cell> neighbors;
    
    public Cell() {
        this(0, Color.WHITE);
        neighbors = new HashSet<Cell>();
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
    
    public void setNeighbors(Collection<? extends Cell> neighbors) {
        this.neighbors = neighbors;
    }
    
    public Collection<? extends Cell> getNeighbors() {
        return Collections.unmodifiableCollection(neighbors);
    }
    
    protected void setState(int state, Color color) {
        this.state = state;
        this.color = color;
    }
}
