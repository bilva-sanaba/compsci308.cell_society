package grid;

import java.util.HashSet;
import java.util.Set;

import cellsociety.Cell;
import cellsociety.Grid;
import cellsociety.ShapeGenerator;
import shapegenerator.SquareGenerator;

public class CardinalRectangleGrid<E extends Cell> extends Grid<E>{

	@Override
    public ShapeGenerator getShapeGenerator(double width) {
        return new SquareGenerator(width, this);
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
	protected Set<E> findNeighbor(int row, int col) {
		 Set<E> neighbors = new HashSet<E>();
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
