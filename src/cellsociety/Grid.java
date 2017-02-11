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
import grid.Location;
import grid.neighborfinder.HexagonFinder;
import grid.neighborfinder.NeighborFinder;

/**
 * Superclass of grid that contains cells in the simulation
 * @author Mike Liu
 * 
 */
public abstract class Grid {

    private Cell[][] sim;
    private NeighborFinder myFinder;
    
    public Grid(int row, int col, Collection<CellConfig> cellConfig, CellGenerator generator) {
        sim = new Cell[row][col];
        for(CellConfig config: cellConfig) {
            sim[config.getRow()][config.getCol()] = generator.getCell(config.getState());
        }
        fillEmpty(generator);
        myFinder = new HexagonFinder();
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
                Set<Cell> neighbors = new HashSet<Cell>();
                for(Location loc: myFinder.findNeighbor(row, col, diagonal)) {
                    addNeighbor(loc.getRow(), loc.getCol(), neighbors);
                }
                sim[row][col].setNeighbors(neighbors);
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
    
    protected abstract void addNeighbor(int row, int col, Collection<Cell> neighbors);

    private void fillEmpty(CellGenerator generator) {
        for(int i = 0; i < numRows(); i++) {
            for(int j = 0; j < numCols(); j++) {
                if(sim[i][j] == null) {
                    sim[i][j] = generator.getBasicCell();
                }
            }
        }
    }
}
