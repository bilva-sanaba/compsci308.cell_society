package cellsociety;

import java.util.Set;

public abstract class Grid<E extends Cell> {

    E[][] sim;
    
    public Grid() {
        //TODO: initialize sim
    }
    
    public int numRows() {
        return sim.length;
    }
    
    public int numCols(int row) {
        return sim[row].length;
    }
    
    public E get(int row, int col) {
        return sim[row][col];
    }
    
    public void set(int row, int col, E cell) {
        sim[row][col] = cell;
    }
    
    public abstract Set<E> findNeighbors(int row, int col);
    
}
