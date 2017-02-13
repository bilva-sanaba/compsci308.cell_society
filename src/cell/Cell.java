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
 *
 */
public abstract class Cell {
    
    private CellState myState, nextState;
    private Set<Cell> neighbors;
    
    public Cell(CellState state) {
        myState = nextState = state;
        neighbors = new HashSet<Cell>();
    }
    
    public CellState getState() {
        return myState;
    }
    
    public boolean is(CellState state) {
        return myState.equals(state);
    }
    
    public void rotateState() {
        myState = nextState = myState.rotate();
    }
    
    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof Cell)) {
            return false;
        }
        return myState.equals(((Cell)other).myState);
    }
    
    public void setNeighbors(Collection<Cell> neighbors) {
        this.neighbors.clear();
        this.neighbors.addAll(neighbors);
    }

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
    
    protected void setNextState(CellState state) {
        nextState = state;
    }
}
