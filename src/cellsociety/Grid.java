package cellsociety;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cell.Cell;
import cell.CellConfig;
import cell.generator.CellGenerator;
import cell.state.CellState;
import grid.FlatGrid;
import grid.Location;
import grid.ToroidalGrid;
import grid.neighborfinder.HexagonFinder;
import grid.neighborfinder.NeighborFinder;
import grid.neighborfinder.RectangleFinder;
import grid.neighborfinder.TriangleFinder;

/**
 * Superclass of grid that contains cells in the simulation
 * Treat cells as rectangles by default
 * @author Mike Liu
 * 
 */
public abstract class Grid {

    public static final List<String> GRID_TYPE = Arrays.asList(
            "Finite",
            "Toroidal");
    public static final List<NeighborFinder> NEIGHBOR_FINDER = Arrays.asList(
            new RectangleFinder(),
            new TriangleFinder(),
            new HexagonFinder());

    private Cell[][] sim;
    private boolean isDiagonal;
    private NeighborFinder myFinder;
    
    public Grid(int row, int col, Collection<CellConfig> cellConfig, CellGenerator generator, boolean diagonal) {
        sim = new Cell[row][col];
        isDiagonal = diagonal;
        for(CellConfig config: cellConfig) {
            sim[config.getRow()][config.getCol()] = generator.getCell(config.getState());
        }
        fillEmpty(generator);
        myFinder = NEIGHBOR_FINDER.get(0);
        buildNeighborGraph();
    }
    
    public Grid(Grid other) {
        sim = other.sim;
        isDiagonal = other.isDiagonal;
        myFinder = other.myFinder;
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
    
    public void setDiagonal(boolean diagonal) {
        isDiagonal = diagonal;
        buildNeighborGraph();
    }
    
    public void setShape(int index) {
        try {
            myFinder = NEIGHBOR_FINDER.get(index);
        } catch(IndexOutOfBoundsException e) {
            throw new CAException(CAException.INVALID_SHAPE);
        }
        buildNeighborGraph();
    }
    
    public void update() {
        for(int row = 0; row < numRows(); row++) {
            for(int col = 0; col < numCols(); col++) {
                sim[row][col].update();
            }
        }
    }
    
    public void buildNeighborGraph() {
        for(int row = 0; row < sim.length; row++) {
            for(int col = 0; col < sim[0].length; col++) {
                Set<Cell> neighbors = new HashSet<Cell>();
                for(Location loc: myFinder.findNeighbor(row, col, isDiagonal)) {
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
    
    public Grid switchGrid(String type) {
        if(type.equals(GRID_TYPE.get(0))) {
            return new FlatGrid(this);
        }
        else if(type.equals(GRID_TYPE.get(1))) {
            return new ToroidalGrid(this);
        }
        throw new CAException(CAException.INVALID_GRID, type);
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
