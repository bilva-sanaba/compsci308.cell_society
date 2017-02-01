package model;

import java.util.Set;

import cell.GOLCell;
import cellsociety.Cell;
import cellsociety.Grid;
import cellsociety.Model;

public class GOLModel extends Model {
    
    public static final int LOWER_THRESHOLD = 2;
    public static final int UPPER_THRESHOLD = 3;

    @Override
    public void step() {
        Grid<GOLCell> newGrid = new RectangleGrid<GOLCell>();
        Grid<GOLCell> grid = (Grid<GOLCell>) getGrid();
        for(int row = 0; row < grid.numRows(); row++) {
            for(int col = 0; col < grid.numCols(row); col++) {
                newGrid.set(row, col, changeState(grid.get(row, col), grid.findNeighbors(row, col)));
                
            }
        }
    }

    private GOLCell changeState(GOLCell cell, Set<GOLCell> neighbors) {
        int count = 0;
        for(Cell n: neighbors) {
            if(n.getState() == GOLCell.LIVE) {
                count++;
            }
        }
        if(cell.getState() == GOLCell.LIVE && (count < LOWER_THRESHOLD || count > UPPER_THRESHOLD)) {
            return GOLCell.dead();
        }
        else if(cell.getState() == GOLCell.DEAD && count == UPPER_THRESHOLD) {
            return GOLCell.live();
        }
        return cell;
    }
}
