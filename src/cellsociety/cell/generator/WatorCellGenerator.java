package cellsociety.cell.generator;

import cellsociety.CAException;
import cellsociety.cell.WatorCell;
import cellsociety.cell.state.WatorState;

/**
 * Generates cells for Wator simulation
 * @author Mike Liu
 *
 */
public class WatorCellGenerator implements CellGenerator {

    @Override
    public WatorCell getBasicCell() {
        return new WatorCell(WatorState.WATER);
    }

    @Override
    public WatorCell getCell(int state) {
        try {
            return new WatorCell(WatorState.STATES.get(state));
        } catch(IndexOutOfBoundsException e) {
            throw new CAException(CAException.INVALID_CELL, "Game of Life");
        }
    }
}
