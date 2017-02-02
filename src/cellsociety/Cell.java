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
    
    /**
     * Updates the cell to the set next state
     * User should set the next state of all cells and then call update on all cells
     * to perform simultaneous update
     */
    public void update() {
        state = nextState;
        color = nextColor;
    }
    
    protected void setNextState(int state, Color color) {
        nextState = state;
        nextColor = color;
    }
}
