package cell.generator;

import cell.GOLCell;
import cell.state.GOLState;
import cellsociety.CAException;

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
