package model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cell.Cell;
import cell.GOLCell;
import cell.generator.GOLCellGenerator;
import cell.state.GOLState;
import grid.FlatGrid;
import util.CAData;

/**
 * Model for Game of Life simulation
 * @author Mike Liu
 *
 */
public class GOLModel extends Model {
    
    public static final String NAME = "gol";
    public static final List<String> DOCUMENTED_STATES = Arrays.asList(GOLState.LIVE.toString());
    
    public static final double LOWER_THRESHOLD = .25;
    public static final double UPPER_THRESHOLD = .375;
    
    public GOLModel(CAData data) {
        super(new FlatGrid(data.numRows(), data.numCols(), data.getCell(), new GOLCellGenerator()));
    }

    @Override
    public String getName() {
        return NAME;
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
        double count = 0;
        for(Cell c: cell.getNeighbors()) {
            if(c.is(GOLState.LIVE)) {
                count++;
            }
        }
        double percent = count/getGrid().numNeighbors();
        if(cell.is(GOLState.LIVE) && (percent < LOWER_THRESHOLD || percent > UPPER_THRESHOLD)) {
            cell.die();
            addCount(GOLState.LIVE, -1);
            addCount(GOLState.DEAD, 1);
        }
        else if(cell.is(GOLState.DEAD) && count == Math.floor(UPPER_THRESHOLD * getGrid().numNeighbors())) {
            cell.spawn();
            addCount(GOLState.DEAD, -1);
            addCount(GOLState.LIVE, 1);
        }
    }
}
