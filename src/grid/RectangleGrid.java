package grid;

import java.util.HashSet;
import java.util.Set;

import cell.CellGenerator;
import cellsociety.Cell;
import cellsociety.Grid;

public class RectangleGrid extends Grid {
		
	public RectangleGrid(CellGenerator generator) {
        super(generator);
    }

    private boolean isLeftEdge(int row, int col){
		return (col == 0);
	}
	
	private boolean isRightEdge(int row, int col){
		return (col == numCols());
	}
	
	private boolean isBottomEdge(int row, int col){
		return (row == numRows());
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
        if (!isLeftEdge(row,col) && !isTopEdge(row,col)){
            neighbors.add(get(row-1,col-1));
        }
        if (!isRightEdge(row,col) && !isTopEdge(row,col)){
            neighbors.add(get(row-1,col+1));
        }
        if (!isLeftEdge(row,col) && !isBottomEdge(row,col)){
            neighbors.add(get(row+1,col-1));
        }
        if (!isRightEdge(row,col) && !isBottomEdge(row,col)){
            neighbors.add(get(row+1,col+1));
        }
        return neighbors;
    }
	
}


	
