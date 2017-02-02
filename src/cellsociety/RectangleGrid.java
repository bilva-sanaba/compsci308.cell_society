package cellsociety;

import java.util.HashSet;
import java.util.Set;

public class RectangleGrid extends Grid{
		
	private boolean isLeftEdge(int row, int col){
		return (col ==0);
	}
	private boolean isRightEdge(int row, int col){
		return (col == sim[0].length);
	}
	private boolean isBottomEdge(int row, int col){
		return (row == sim.length );
	}
	private boolean isTopEdge(int row, int col){
		return (row==0);
	}
	

	public void addNeighbors(int row, int col) {
		  Set<Cell> neighbors = new HashSet<Cell>();
		  Integer[] key = new Integer[2];
		  key[0] = row;
		  key[1] = col;
		  NeighborMap.put(key, new HashSet<Cell>());
		  if (!isLeftEdge(row,col)){
			  neighbors.add(sim[row][col-1]);
		  }
		  if (!isRightEdge(row,col)){
			  neighbors.add(sim[row][col+1]);
		  }
		  if (!isTopEdge(row,col)){
			  neighbors.add(sim[row-1][col]);
		  }
		  if (!isBottomEdge(row,col)){
			  neighbors.add(sim[row+1][col]);
		  }
		  if (!isLeftEdge(row,col) && !isTopEdge(row,col)){
			  neighbors.add(sim[row-1][col-1]);
		  }
		  if (!isRightEdge(row,col) && !isTopEdge(row,col)){
			  neighbors.add(sim[row-1][col+1]);
		  }
		  if (!isLeftEdge(row,col) && !isBottomEdge(row,col)){
			  neighbors.add(sim[row+1][col-1]);
		  }
		  if (!isRightEdge(row,col) && !isBottomEdge(row,col)){
			  neighbors.add(sim[row+1][col+1]);
		  }
		  for (Cell cell : neighbors){
			  NeighborMap.get(key).add(cell);
		  }
		    
		}
	
}


	
