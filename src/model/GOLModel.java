package model;

import cell.GOLCell;
import cellsociety.Cell;
import cellsociety.Grid;
import cellsociety.Model;

/**
 * Model for Game of Life simulation
 * @author Mike Liu
 *
 */
public class GOLModel extends Model {
    
    public static final int LOWER_THRESHOLD = 2;
    public static final int UPPER_THRESHOLD = 3;
    
    public GOLModel() {
        //TODO
    }

    @Override
    public void step() {
        Grid<GOLCell> grid = (Grid<GOLCell>) getGrid();
        for(int row = 0; row < grid.numRows(); row++) {
            for(int col = 0; col < grid.numCols(); col++) {
                changeState(grid.get(row, col));
            }
        }
        for(int row = 0; row < grid.numRows(); row++) {
            for(int col = 0; col < grid.numCols(); col++) {
                grid.get(row, col).update();;
            }
        }
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
