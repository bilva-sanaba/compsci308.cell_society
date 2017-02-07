package grid;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import cell.CellConfig;
import cell.CellGenerator;
import cellsociety.Cell;
import cellsociety.Grid;

public class CardinalRectangleGrid extends Grid {
    
    public CardinalRectangleGrid(int row, int col, Collection<CellConfig> cellConfig, CellGenerator generator) {
        super(row, col, cellConfig, generator);
    }
    
	private boolean isLeftEdge(int row, int col){
		return (col == 0);
	}
	
	private boolean isRightEdge(int row, int col){
		return (col == numCols()-1);
	}
	
	private boolean isBottomEdge(int row, int col){
		return (row == numRows()-1);
	}
	
	private boolean isTopEdge(int row, int col){
		return (row == 0);
	}

	@Override
	protected Set<Cell> findNeighbor(int row, int col) {
		 Set<Cell> neighbors = new HashSet<Cell>();
	     if (!isLeftEdge(row,col)){
	            neighbors.add(get(row,col-1));
	     }
	     if (!isRightEdge(row,col)){
	            neighbors.add(get(row,col+1));
	     }
	     if (!isTopEdge(row,col)){
	    	 neighbors.add(get(row-1,col));
	     }
	     if (!isBottomEdge(row,col)){
	    	 neighbors.add(get(row+1,col));
	     }
	     return neighbors;
	}

}
