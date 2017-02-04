package model;

import cell.GOLCell;
import cellsociety.Cell;
import cellsociety.Model;
import cellsociety.cellgenerator.GOLCellGenerator;

/**
 * Model for Game of Life simulation
 * @author Mike Liu
 *
 */
public class GOLModel extends Model {
    
    public static final int LOWER_THRESHOLD = 2;
    public static final int UPPER_THRESHOLD = 3;
    
    public GOLModel() {
        super(new GOLCellGenerator());
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
            if(c.getState() == GOLCell.LIVE) {
                count++;
            }
        }
        if(cell.getState() == GOLCell.LIVE && (count < LOWER_THRESHOLD || count > UPPER_THRESHOLD)) {
            cell.die();
        }
        else if(cell.getState() == GOLCell.DEAD && count == UPPER_THRESHOLD) {
            cell.spawn();
        }
    }
}
