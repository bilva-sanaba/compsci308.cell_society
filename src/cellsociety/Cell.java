package cellsociety;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.paint.Color;

/**
 * Superclass for individual cell in the grid
 * Keeps track of state and color
 * @author Mike Liu
 *
 */
public abstract class Cell {
    
    private int state, nextState;
    private Color color, nextColor;
    private Set<Cell> neighbors;
    
    public Cell() {
        this(0, Color.WHITE);
        neighbors = new HashSet<Cell>();
    }
    
    public Cell(int state, Color color) {
        this.state = state;
        this.color = color;
    }
    
    public void update() {
        state = nextState;
        color = nextColor;
    }
    
    public int getState() {
        return state;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setNeighbors(Collection<? extends Cell> neighbors) {
        this.neighbors.clear();
        this.neighbors.addAll(neighbors);
    }
    
    public Collection<? extends Cell> getNeighbors() {
        return Collections.unmodifiableCollection(neighbors);
        
    }
    
    protected void setState(int state, Color color) {
        nextState = state;
        nextColor = color;
    }
}
