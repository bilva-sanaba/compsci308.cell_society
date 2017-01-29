package cellsociety;

import java.util.Set;

public abstract class Grid {

    Cell[][] sim;
    
    public Grid() {
        //TODO: initialize sim
    }
    
    public int numRows() {
        return sim.length;
    }
    
    public int numCols() {
        return sim[0].length;
    }
    
    public Cell get(int row, int col) {
        return sim[row][col];
    }
    
    public abstract Set<Cell> findNeighbors(int row, int col);
    
}
