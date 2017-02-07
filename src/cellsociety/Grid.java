package cellsociety;

import java.util.Collection;
import java.util.Set;

import cell.CellConfig;
import cell.CellGenerator;

/**
 * Superclass of grid that contains cells in the simulation
 * @author Mike Liu
 * 
 */
public abstract class Grid {

    private Cell[][] sim;
    
    public Grid(int row, int col, Collection<CellConfig> cellConfig, CellGenerator generator) {
        sim = new Cell[row][col];
        for(CellConfig config: cellConfig) {
            sim[config.getRow()][config.getCol()] = generator.getCell(config.getState());
        }
        fillEmpty(generator);
        buildNeighborGraph();
        for(int i = 0; i < numRows(); i++) {
            for(int j = 0; j < numCols(); j++) {
                
            }
        }
    }

    public Grid(Grid other) {
        sim = other.sim;
        buildNeighborGraph();
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
    
    public void update() {
        for(int row = 0; row < numRows(); row++) {
            for(int col = 0; col < numCols(); col++) {
                sim[row][col].update();
            }
        }
    }
    
    /**
     * Finds the neighbors of the cell at location (row, col)
     * Implemented by subclasses only for the purpose of build neighbor graph
     * Should not be called for other classes
     * @param row - the row of grid that the cell is in
     * @param col - the column of grid that the cell is in
     * @return
     */
    protected abstract Set<Cell> findNeighbor(int row, int col);

    private void fillEmpty(CellGenerator generator) {
        for(int i = 0; i < numRows(); i++) {
            for(int j = 0; j < numCols(); j++) {
                if(sim[i][j] == null) {
                    sim[i][j] = generator.getBasicCell();
                }
            }
        }
    }
    
    private void buildNeighborGraph() {
        for(int row = 0; row < sim.length; row++) {
            for(int col = 0; col < sim[0].length; col++) {
                sim[row][col].setNeighbors(findNeighbor(row, col));
            }
        }
    }
    
}
