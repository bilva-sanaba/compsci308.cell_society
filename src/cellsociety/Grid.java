package cellsociety;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Grid {

    Cell[][] sim;
    Map <Integer[], Set<Cell>> NeighborMap = new HashMap<Integer[], Set<Cell>>();
    
    public Grid() {
        //TODO: create Grid based on XML Data, then loop through all cell coordinates and add neighbors
    	
    }
    
    public int numRows() {
        return sim.length;
    }
    
    public int numCols(int row) {
        return sim[row].length;
    }
    
    public Cell get(int row, int col) {
        return sim[row][col];
    }
    public abstract void addNeighbors(int row, int col);
    
    public Set<Cell> getNeighbors(int row, int col){
    	Integer[] key = new Integer[2];
    	key[0] = row;
    	key[1] = col;
    	return NeighborMap.get(key);
    }
    
}
