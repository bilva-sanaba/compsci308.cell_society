package model;

import cell.SegregationCell;
import cell.WatorCell;
import cellsociety.Cell;
import cellsociety.Model;
import grid.RectangleGrid;

public class SegregationModel extends Model {

    private int relocateRed = 0;
    private int relocateBlue = 0;
    public static final double happyPercent = .3;

    public SegregationModel() {
        super(new RectangleGrid(WatorCell.getGenerator()));
    }

    @Override
    public void update() {
        for (int row = 0; row < getGrid().numRows(); row++) {
            for (int col = 0; col < getGrid().numCols(); col++) {
                removeUnhappy((SegregationCell) getGrid().get(row, col));
            }
        }
        moveUnhappy();
        getGrid().update();
    }

    private void moveUnhappy() {
        for (int row = 0; row < getGrid().numRows(); row++) {
            for (int col = 0; col < getGrid().numCols(); col++) {
                while (relocateRed != 0) {
                    SegregationCell currentCell = (SegregationCell) getGrid().get(row, col);
                    if (currentCell.inState(SegregationCell.EMPTY)) {
                        currentCell.fillRed();
                        relocateRed--;
                    }
                }
                while (relocateBlue != 0) {
                    SegregationCell currentCell = (SegregationCell) getGrid().get(row, col);
                    if (currentCell.inState(SegregationCell.EMPTY)) {
                        currentCell.fillBlue();
                        relocateBlue--;
                    }
                }
            }
        }
    }

    private void removeUnhappy(SegregationCell cell) {
        int numberOfNeighbors = 0;
        int numberSameNeighbors = 0;
        if (!cell.inState(SegregationCell.EMPTY)) {
            for (Cell c : cell.getNeighbors()) {
                numberOfNeighbors++;
                if (cell.hasSameState(c)) {
                    numberSameNeighbors++;
                }
            }
            if ((double) numberSameNeighbors / (double) numberOfNeighbors < happyPercent) {
                if (cell.inState(SegregationCell.RED)) {
                    relocateRed++;
                } else {
                    relocateBlue++;
                }
                cell.leave();
            }
        }
    }
}
