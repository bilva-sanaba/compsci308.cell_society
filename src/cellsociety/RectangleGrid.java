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
//		    if (!isLeftEdge(row,col)){
//		    	neighbors.add(sim[]))
//		    	}
//		    }
//		    else {
//		    	if (this.isOnEdge(row, col)){
//		    		
//		    		
//		    	}
//		    else{
//		    	for (int r = -1; r<=1;r++){
//		    		for (int c =-1; r<=1;c++){
//		    		if (r!=0 && c!=0){
//		    			neighbors.add(sim[row+r][col+c]);
//		    		}
//		    		}
//		    	}
//		    }
		   NeighborMap.put(key,neighbors);
		    
		    
		}
	
}


	
