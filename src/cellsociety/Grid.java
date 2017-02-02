package cellsociety;

import java.util.Collection;
import java.util.Set;

/**
 * Superclass of grid that contains cells in the simulation
 * @author Mike Liu
 *
 * @param <E> The type of object that is contained by the grid. Must extend Cell.
 */
public abstract class Grid<E extends Cell> {

    private E[][] sim;
    private Collection<E>[][] neighborGraph;
    
    public Grid() {
        //TODO: create Grid based on XML Data, then loop through all cell coordinates and add neighbors
        neighborGraph = buildNeighborGraph();
    }
    
    public int numRows() {
        return sim.length;
    }
    
    public int numCols() {
        return sim[0].length;
    }
    
    public E get(int row, int col) {
        return sim[row][col];
    }
    
    public void set(int row, int col, E cell) {
        sim[row][col] = cell;
    }
    
    public Collection<E> getNeighbors(int row, int col) {
        return neighborGraph[row][col];
    }
    
    public abstract ShapeGenerator getShapeGenerator(double width);
    
    /**
     * Finds the neighbors of the cell at location (row, col)
     * Implemented by subclasses only for the purpose of build neighbor graph
     * Should not be called for other classes
     * @param row - the row of grid that the cell is in
     * @param col - the column of grid that the cell is in
     * @return
     */
    protected abstract Set<E> findNeighbor(int row, int col);
    
    private Collection<E>[][] buildNeighborGraph() {
        Collection<E>[][] ret = (Collection<E>[][]) new Collection[numRows()][numCols()];
        for(int row = 0; row < sim.length; row++) {
            for(int col = 0; col < sim[0].length; col++) {
                ret[row][col] = findNeighbor(row, col);
            }
        }
        return ret;
    }
    
}
