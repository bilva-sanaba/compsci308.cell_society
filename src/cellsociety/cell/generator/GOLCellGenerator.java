package cellsociety.cell.generator;

import cellsociety.CAException;
import cellsociety.cell.GOLCell;
import cellsociety.cell.state.GOLState;

/**
 * Generates cells for Game of Life simulation
 * @author Mike Liu
 *
 */
public class GOLCellGenerator implements CellGenerator {

    @Override
    public GOLCell getBasicCell() {
        return new GOLCell(GOLState.DEAD);
    }

    @Override
    public GOLCell getCell(int state) {
        try {
            return new GOLCell(GOLState.STATES.get(state));
        } catch(IndexOutOfBoundsException e) {
            throw new CAException(CAException.INVALID_CELL, "Game of Life");
        }
    }
}
