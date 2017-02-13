package cellsociety.cell;

import cellsociety.cell.state.GOLState;

/**
 * Cell for Game of Life simulation
 * @author Mike Liu
 *
 */
public class GOLCell extends Cell {
    
    public GOLCell(GOLState state) {
        super(state);
    }
    
    public void spawn() {
        setNextState(GOLState.LIVE);
    }
    
    public void die() {
        setNextState(GOLState.DEAD);
    }
}
