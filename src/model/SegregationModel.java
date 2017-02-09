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
		super(new RectangleGrid(data.numRows(), data.numCols(), data.getCell(), SegregationCell.getGenerator(), true));
		happyPercent = ((SegregationData)data).getThreshold();
	}
	
	public void setThreshold(double threshold) {
	    happyPercent = threshold;
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
			if (cell.is(SegregationCell.RED)) {
				relocateRed++;
			} else {
				relocateBlue++;
			}
			cell.leave();
		}
	}
	private void moveUnhappy() {
		while (relocateRed != 0) {
			((SegregationCell) pickRandomCell(getGrid().getCells(SegregationCell.EMPTY))).fillRed();
			relocateRed--;
		}
		while (relocateBlue != 0) {
			((SegregationCell) pickRandomCell(getGrid().getCells(SegregationCell.EMPTY))).fillBlue();
			relocateBlue--;
		}
	}

	private boolean isUnhappy(Cell cell){
		int numberOfNeighbors = 0;
		int numberSameNeighbors = 0;
		if (!cell.is(SegregationCell.EMPTY)) {
			for (Cell c : cell.getNeighbors()) {
				if (!c.is(SegregationCell.EMPTY)) {
					numberOfNeighbors++;
					if (cell.equals(c)) {
						numberSameNeighbors++;
					}
				}
			}
			return (numberOfNeighbors>0 && (double) numberSameNeighbors / (double) numberOfNeighbors < happyPercent); 
		}
		return false;
	}
}
