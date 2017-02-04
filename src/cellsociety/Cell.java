package cellsociety;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import cell.CellState;
import javafx.scene.paint.Color;

/**
 * Superclass for individual cell in the grid
 * Keeps track of state and color
 * @author Mike Liu
 *
 */
public abstract class Cell {
    
    private CellState myState, nextState;
    private Set<Cell> neighbors;
    
    public Cell(CellState state) {
        myState = state;
        neighbors = new HashSet<Cell>();
    }
    
    public boolean inState(CellState state) {
        return myState.equals(state);
    }
    
    public boolean hasSameState(Cell other) {
        return myState == other.myState;
    }
    
    public Color getColor() {
        return myState.getColor();
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
        myState = nextState;
    }
    
    protected void setNextState(CellState state) {
        nextState = state;
    }
}
