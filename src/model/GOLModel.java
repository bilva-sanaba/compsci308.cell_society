package model;

import cell.Cell;
import cell.GOLCell;
import cell.generator.GOLCellGenerator;
import cell.state.GOLState;
import cellsociety.Model;
import grid.RectangleGrid;
import util.CAData;

/**
 * Model for Game of Life simulation
 * @author Mike Liu
 *
 */
public class GOLModel extends Model {
    
    public static final String NAME = "gol";
    public static final int LOWER_THRESHOLD = 2;
    public static final int UPPER_THRESHOLD = 3;
    
    public GOLModel(CAData data) {
        super(new RectangleGrid(data.numRows(), data.numCols(), data.getCell(), new GOLCellGenerator(), true));
    }

    @Override
    public void update() {
        for(int row = 0; row < getGrid().numRows(); row++) {
            for(int col = 0; col < getGrid().numCols(); col++) {
                changeState((GOLCell)getGrid().get(row, col));
            }
        }
        getGrid().update();
    }

    private void changeState(GOLCell cell) {
        int count = 0;
        for(Cell c: cell.getNeighbors()) {
            if(c.is(GOLState.LIVE)) {
                count++;
            }
        }
        if(cell.is(GOLState.LIVE) && (count < LOWER_THRESHOLD || count > UPPER_THRESHOLD)) {
            cell.die();
        }
        else if(cell.is(GOLState.DEAD) && count == UPPER_THRESHOLD) {
            cell.spawn();
        }
    }
}
