package cellsociety;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cell.Cell;
import cell.CellConfig;
import cell.generator.CellGenerator;
import cell.state.CellState;
import grid.NeighborOffset;

/**
 * Superclass of grid that contains cells in the simulation
 * @author Mike Liu
 * 
 */
public abstract class Grid {

    private Cell[][] sim;
    private boolean isTorodial;
    
    public Grid(int row, int col, Collection<CellConfig> cellConfig, CellGenerator generator) {
        sim = new Cell[row][col];
        for(CellConfig config: cellConfig) {
            sim[config.getRow()][config.getCol()] = generator.getCell(config.getState());
        }
        fillEmpty(generator);
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
    
    public void buildNeighborGraph(boolean diagonal) {
        for(int row = 0; row < sim.length; row++) {
            for(int col = 0; col < sim[0].length; col++) {
                sim[row][col].setNeighbors(findNeighbor(row, col, diagonal));
            }
        }
    }
    
    public List<Cell> getCells(CellState state){
        List<Cell> cells = new ArrayList<Cell>();
        for (int row = 0; row < numRows(); row++) {
            for (int col = 0; col < numCols(); col++) {
                if (get(row, col).is(state)){
                    cells.add(get(row, col));
                }
            }
        }
        return cells;
    }
    
    /**
     * Finds the neighbors of the cell at location (row, col)
     * Implemented by subclasses only for the purpose of build neighbor graph
     * Should not be called for other classes
     * @param row - the row of grid that the cell is in
     * @param col - the column of grid that the cell is in
     * @return
     */
    protected abstract Collection<Cell> findNeighbor(int row, int col, boolean diagonal);
    
    protected Collection<Cell> findNeighbor(int row, int col, boolean diagonal,
            NeighborOffset cardinalOffset, NeighborOffset diagonalOffset) {
        Collection<Cell> neighbors = findNeighbor(row, col, cardinalOffset);
        if(diagonal) {
            neighbors.addAll(findNeighbor(row, col, diagonalOffset));
        }
        return neighbors;
    }
    
    protected Collection<Cell> findNeighbor(int row, int col, NeighborOffset offset) {
        Set<Cell> neighbors = new HashSet<Cell>();
        for(int i = 0; i < offset.length(); i++) {
            int r = row + offset.getRowOffset(i);
            int c = col + offset.getColOffset(i);
            if(inBounds(r, c)) {
                neighbors.add(get(r, c));
            }
            else if(isTorodial) {
                neighbors.add(get(r % numRows(), c % numCols()));
            }
        }
        return neighbors;
    }

    private void fillEmpty(CellGenerator generator) {
        for(int i = 0; i < numRows(); i++) {
            for(int j = 0; j < numCols(); j++) {
                if(sim[i][j] == null) {
                    sim[i][j] = generator.getBasicCell();
                }
            }
        }
    }
    
	private boolean inBounds(int row, int col){
		return row >=0 && row < numRows() && col >= 0 && col < numCols();
	} 
}
