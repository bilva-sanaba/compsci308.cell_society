package model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cell.Cell;
import cell.GOLCell;
import cell.generator.GOLCellGenerator;
import cell.state.GOLState;
import cellsociety.Model;
import grid.FlatGrid;
import util.CAData;

/**
 * Model for Game of Life simulation
 * @author Mike Liu
 *
 */
public class GOLModel extends Model {
    
    public static final String NAME = "gol";
    public static final List<String> DOCUMENTED_STATES = Arrays.asList(
            GOLState.DEAD.toString(),
            GOLState.LIVE.toString());
    
    public static final int LOWER_THRESHOLD = 2;
    public static final int UPPER_THRESHOLD = 3;
    
    public GOLModel(CAData data) {
        super(new FlatGrid(data.numRows(), data.numCols(), data.getCell(), new GOLCellGenerator(), true));
    }

    @Override
    public void update() {
        for(Cell cell: getGrid()) {
            changeState((GOLCell)cell);
        }
        getGrid().update();
    }
    
    @Override
    protected Collection<String> getDocumentedStates() {
        return DOCUMENTED_STATES;
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
            addCount(GOLState.LIVE, -1);
            addCount(GOLState.DEAD, 1);
        }
        else if(cell.is(GOLState.DEAD) && count == UPPER_THRESHOLD) {
            cell.spawn();
            addCount(GOLState.DEAD, -1);
            addCount(GOLState.LIVE, 1);
        }
    }
}
