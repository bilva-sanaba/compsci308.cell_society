package cellsociety.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cellsociety.cell.Cell;
import cellsociety.cell.SegregationCell;
import cellsociety.cell.generator.SegregationCellGenerator;
import cellsociety.cell.state.SegregationState;
import cellsociety.grid.FlatGrid;
import util.CAData;
import util.SegregationData;

/**
 * Model for Segregation simulation
 * @author Bilva Sanaba
 * @author Mike Liu
 *
 */
public class SegregationModel extends Model {

	public static final String NAME = "segregation";
    public static final List<String> DOCUMENTED_STATES = Arrays.asList("Unhappy");
    
	public static final double DEFAULT_THRESHOLD = .3;

	private double happyPercent;

	public SegregationModel(CAData data) {
		super(new FlatGrid(data.numRows(), data.numCols(), data.getCell(), new SegregationCellGenerator()));
		happyPercent = ((SegregationData)data).getThreshold();
		checkHappiness();
	}

    @Override
    public String getName() {
        return NAME;
    }

	/**
	 * Updates the threshold needed for a cell to move.
	 * @param threshold
	 */
    public void setThreshold(double threshold) {
	    happyPercent = threshold;
	}

    /**
     * Updates the simulation by moving all unhappy cells and then placing the appropriate number and type of moved cells in random locations
     */
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
			List<Cell> empty = getGrid().getCells(SegregationState.EMPTY);
		    if(empty.size() > 0) {
		        SegregationCell emptyCell = (SegregationCell) pickRandomCell(empty);
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
			if (numberOfNeighbors==0){return true;}
			return (numberOfNeighbors>0 && numberSameNeighbors/numberOfNeighbors < happyPercent); 
		}
		return false;
	}
}
