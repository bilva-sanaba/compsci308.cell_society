package cell.generator;

import cell.WatorCell;
import cell.state.WatorState;
import cellsociety.CAException;

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
