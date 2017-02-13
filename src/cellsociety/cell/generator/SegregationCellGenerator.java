package cellsociety.cell.generator;

import cellsociety.CAException;
import cellsociety.cell.SegregationCell;
import cellsociety.cell.state.SegregationState;

/**
 * Generates cells for Segregation simulation
 * @author Mike Liu
 *
 */
public class SegregationCellGenerator implements CellGenerator {

    @Override
    public SegregationCell getBasicCell() {
        return new SegregationCell(SegregationState.EMPTY);
    }

    @Override
    public SegregationCell getCell(int state) {
        try {
            return new SegregationCell(SegregationState.STATES.get(state));
        } catch(IndexOutOfBoundsException e) {
            throw new CAException(CAException.INVALID_CELL, "Game of Life");
        }
    }
}
