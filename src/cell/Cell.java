package cell;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import cell.state.CellState;

/**
 * Superclass for individual cell in the grid
 * Keeps track of state and color
 * @author Mike Liu
 * @author Bilva Sanaba
 *
 */
public abstract class Cell {
    
    private CellState myState, nextState;
    private Set<Cell> neighbors;
    
    public Cell(CellState state) {
        myState = nextState = state;
        neighbors = new HashSet<Cell>();
    }
    
    /**
     * Returns the current state of the cell
     * @return current state of the cell
     */
    public CellState getState() {
        return myState;
    }
    
    /**
     * Returns whether the cell is in the query state
     * @param state
     * @return
     */
    public boolean is(CellState state) {
        return myState.equals(state);
    }
    
    /**
     * Rotates the cell to its next available state
     */
    public void rotateState() {
        myState = nextState = myState.rotate();
    }
    
    /**
     * Sets the neighbors of this cell
     * @param neighbors
     */
    public void setNeighbors(Collection<Cell> neighbors) {
        this.neighbors.clear();
        this.neighbors.addAll(neighbors);
    }
    
    /**
     * Returns the neighbors of this cell
     * @return
     */
    public Collection<Cell> getNeighbors() {
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
    
    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof Cell)) {
            return false;
        }
        return myState.equals(((Cell)other).myState);
    }
    
    protected void setNextState(CellState state) {
        nextState = state;
    }
}
