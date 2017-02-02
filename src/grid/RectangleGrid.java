package grid;

import java.util.HashSet;
import java.util.Set;

import cellsociety.Cell;
import cellsociety.Grid;
import cellsociety.ShapeGenerator;
import shapegenerator.SquareGenerator;

public class RectangleGrid<E extends Cell> extends Grid<E> {
		
	private boolean isLeftEdge(int row, int col){
		return (col ==0);
	}
	
	private boolean isRightEdge(int row, int col){
		return (col == numCols());
	}
	
	private boolean isBottomEdge(int row, int col){
		return (row == numRows());
	}
	
	private boolean isTopEdge(int row, int col){
		return (row==0);
	}
	
    @Override
    public ShapeGenerator getShapeGenerator(double width) {
        return new SquareGenerator(width, this);
    }
    
    @Override
    protected Set<E> findNeighbor(int row, int col) {
        Set<E> neighbors = new HashSet<E>();
//        if (!isLeftEdge(row,col)) {
//            neighbors.add();
//        }
//        else {
//            if (this.isOnEdge(row, col)) {
//                
//            }
//            else {
//                for (int r = -1; r<=1;r++){
//                    for (int c =-1; r<=1;c++){
//                        if (r!=0 && c!=0){
//                            neighbors.add(sim[row+r][col+c]);
//                        }
//                    }
//                }
//            }
//        }
        return neighbors;
    }
	
}


	
