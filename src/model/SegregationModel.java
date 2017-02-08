package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
	private Random rand = new Random();

	public SegregationModel(CAData data) {
		super(new RectangleGrid(data.numRows(), data.numCols(), data.getCell(), SegregationCell.getGenerator()));
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
	private Cell pickRandomCell(List<Cell> blocks){
		int i = 0;
		if (blocks.size()==0){
			return null;
		}
		int random = rand.nextInt(blocks.size());
		for(Cell cell : blocks)
			{
		    if (i == random)
		        return cell;
		    i++;
		}
		return null;
	}
	private List<Cell> getEmptyCells(){
	List<Cell> emptyCells = new ArrayList<Cell>();
		for (int row = 0; row < getGrid().numRows(); row++) {
			for (int col = 0; col < getGrid().numCols(); col++) {
				if (getGrid().get(row, col).inState(SegregationCell.EMPTY)){
					emptyCells.add((SegregationCell) getGrid().get(row, col));
				}
			}
		}
	return emptyCells;
	}
	
	private void moveUnhappy() {
				while (relocateRed != 0) {
					List<Cell> currentEmptyCells = getEmptyCells();
					Cell currentCell = pickRandomCell(currentEmptyCells);
						((SegregationCell) currentCell).fillRed();
						relocateRed--;
					}
				
				while (relocateBlue != 0) {
					List<Cell> currentEmptyCells = getEmptyCells();
					Cell currentCell = pickRandomCell(currentEmptyCells);
						((SegregationCell) currentCell).fillBlue();
						relocateBlue--;
					}
					
				
			}
		
	
	private boolean isUnhappy(SegregationCell cell){
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
		}
