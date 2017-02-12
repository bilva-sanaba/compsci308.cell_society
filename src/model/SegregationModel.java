package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cell.Cell;
import cell.SegregationCell;
import cell.generator.SegregationCellGenerator;
import cell.state.SegregationState;
import cellsociety.Model;
import grid.FlatGrid;
import util.CAData;
import util.SegregationData;

public class SegregationModel extends Model {

	public static final String NAME = "segregation";
    public static final List<String> DOCUMENTED_STATES = Arrays.asList("Unhappy");
    
	public static final double DEFAULT_THRESHOLD = .3;

	private double happyPercent;
	private List<SegregationCell> empty;

	public SegregationModel(CAData data) {
		super(new FlatGrid(data.numRows(), data.numCols(), data.getCell(), new SegregationCellGenerator(), true));
		happyPercent = ((SegregationData)data).getThreshold();
		empty = findEmptyCells();
		checkHappiness();
	}

    private List<SegregationCell> findEmptyCells() {
	    List<SegregationCell> empty = new ArrayList<SegregationCell>();
	    for(Cell cell: getGrid()) {
	        if(cell.is(SegregationState.EMPTY)) {
                empty.add((SegregationCell)cell);
            }
	    }
        return empty;
    }

    public void setThreshold(double threshold) {
	    happyPercent = threshold;
	}

	@Override
	public void update() {
	    for(Cell cell: getGrid()) {
	        moveUnhappy((SegregationCell)cell);
	    }
		getGrid().update();
		checkHappiness();
	}
	
    @Override
    protected Collection<String> getDocumentedStates() {
        return DOCUMENTED_STATES;
    }
	
	private void moveUnhappy(SegregationCell cell) {
		if (cell.isUnhappy()){
		    if(empty.size() > 0) {
		        SegregationCell emptyCell = (SegregationCell)pickRandomCell(empty);
	            if (cell.is(SegregationState.RED)) {
	                emptyCell.fillRed();
	            } else {
	                emptyCell.fillBlue();
	            }
	            cell.leave();
	            empty.add(cell);
		    }
		}
	}
    
    private void checkHappiness() {
        resetCount();
        for(Cell cell: getGrid()) {
            if(isUnhappy(cell)) {
                ((SegregationCell)cell).setUnhappy(true);
                addCount(DOCUMENTED_STATES.get(0), 1);
            } else {
                ((SegregationCell)cell).setUnhappy(false);
            }
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
