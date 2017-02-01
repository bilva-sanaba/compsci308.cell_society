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
    
    public int numCols(int row) {
        return sim[row].length;
    }
    
    public Cell get(int row, int col) {
        return sim[row][col];
    }
    
    public void set(int row, int col, Cell cell) {
        sim[row][col] = cell;
    }
    
    public abstract Set<Cell> findNeighbors(int row, int col);
    
}
