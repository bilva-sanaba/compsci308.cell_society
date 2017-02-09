package model;

import cell.SegregationCell;
import cellsociety.Cell;
import cellsociety.Model;
import grid.RectangleGrid;
import util.CAData;
import util.SegregationData;

public class SegregationModel extends Model {

	public static final String NAME = "segregation";
	public static final double DEFAULT_THRESHOLD = .3;

	private int relocateRed = 0;
	private int relocateBlue = 0;
	private double happyPercent;

	public SegregationModel(CAData data) {
		super(new RectangleGrid(data.numRows(), data.numCols(), data.getCell(), SegregationCell.getGenerator()));
		happyPercent = ((SegregationData)data).getThreshold();
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
	
	private void removeUnhappy(SegregationCell cell) {
		if (isUnhappy(cell)){
			if (cell.inState(SegregationCell.RED)) {
				relocateRed++;
			} else {
				relocateBlue++;
			}
			cell.leave();
		}
	}
	private void moveUnhappy() {
		while (relocateRed != 0) {
			((SegregationCell) pickRandomCell(getCertainCells(SegregationCell.EMPTY))).fillRed();
			relocateRed--;
		}
		while (relocateBlue != 0) {
			((SegregationCell) pickRandomCell(getCertainCells(SegregationCell.EMPTY))).fillBlue();
			relocateBlue--;
		}
	}

	private boolean isUnhappy(Cell cell){
		int numberOfNeighbors = 0;
		int numberSameNeighbors = 0;
		if (!cell.inState(SegregationCell.EMPTY)) {
			for (Cell c : cell.getNeighbors()) {
				if (!c.inState(SegregationCell.EMPTY)) {
					numberOfNeighbors++;
					if (cell.hasSameState(c)) {
						numberSameNeighbors++;
					}
				}
			}
			return (numberOfNeighbors>0 && (double) numberSameNeighbors / (double) numberOfNeighbors < happyPercent); 
		}
		return false;
	}
}
