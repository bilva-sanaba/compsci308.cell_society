package model;

import java.util.ArrayList;
import java.util.List;

import cell.Cell;
import cell.SegregationCell;
import cell.generator.SegregationCellGenerator;
import cell.state.SegregationState;
import cellsociety.Model;
import grid.RectangleGrid;
import util.CAData;
import util.SegregationData;

public class SegregationModel extends Model {

	public static final String NAME = "segregation";
	public static final double DEFAULT_THRESHOLD = .3;

	private double happyPercent;
	private List<SegregationCell> empty;

	public SegregationModel(CAData data) {
		super(new RectangleGrid(data.numRows(), data.numCols(), data.getCell(), new SegregationCellGenerator(), true));
		happyPercent = ((SegregationData)data).getThreshold();
		empty = findEmptyCells();
	}
	
	private List<SegregationCell> findEmptyCells() {
	    List<SegregationCell> empty = new ArrayList<SegregationCell>();
	    for (int row = 0; row < getGrid().numRows(); row++) {
            for (int col = 0; col < getGrid().numCols(); col++) {
                Cell cell = getGrid().get(row, col);
                if(cell.is(SegregationState.EMPTY)) {
                    empty.add((SegregationCell)cell);
                }
            }
	    }
        return empty;
    }

    public void setThreshold(double threshold) {
	    happyPercent = threshold;
	}

	@Override
	public void update() {
		for (int row = 0; row < getGrid().numRows(); row++) {
			for (int col = 0; col < getGrid().numCols(); col++) {
				moveUnhappy((SegregationCell) getGrid().get(row, col));
			}
		}
		getGrid().update();
	}
	
	private void moveUnhappy(SegregationCell cell) {
		if (isUnhappy(cell)){
		    SegregationCell emptyCell = (SegregationCell)pickRandomCell(empty);
			if (cell.is(SegregationState.RED)) {
				emptyCell.fillRed();
			} else {
				emptyCell.fillBlue();
			}
			cell.leave();
		}
	}

	private boolean isUnhappy(Cell cell){
		double numberOfNeighbors = 0;
		double numberSameNeighbors = 0;
		if (!cell.is(SegregationState.EMPTY)) {
			for (Cell c : cell.getNeighbors()) {
				if (!c.is(SegregationState.EMPTY)) {
					numberOfNeighbors++;
					if (cell.equals(c)) {
						numberSameNeighbors++;
					}
				}
			}
			return (numberOfNeighbors>0 && numberSameNeighbors/numberOfNeighbors < happyPercent); 
		}
		return false;
	}
}
