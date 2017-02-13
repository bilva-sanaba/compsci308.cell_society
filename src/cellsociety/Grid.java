package cellsociety;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
public abstract class Grid implements Iterable<Cell> {

    public static final List<String> GRID_TYPE = Arrays.asList(
            "Finite",
            "Toroidal");
    public static final List<String> NEIGHBOR_PATTERN = Arrays.asList(
            "Full",
            "Cardinal");
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
    
    /**
     * Returns the number of rows in the grid
     * @return the number of rows in the grid
     */
    public int numRows() {
        return sim.length;
    }

    /**
     * Returns the number of columns in the grid
     * @return the number of columns in the grid
     */
    public int numCols() {
        return sim[0].length;
    }
    
    /**
     * Returns the cell at position (row, col) in the grid
     * @param row
     * @param col
     * @return the cell at position (row, col) in the grid
     */
    public Cell get(int row, int col) {
        return sim[row][col];
    }
    
    /**
     * Sets if the grid should consider diagonal neighbors
     * @param diagonal
     */
    public void setDiagonal(boolean diagonal) {
        isDiagonal = diagonal;
        buildNeighborGraph();
    }
    
    /**
     * Sets the shape of the cells in the grid
     * @param index - refer to NEIGHBOR_FINDER for the available shapes
     */
    public void setShape(int index) {
        try {
            myFinder = NEIGHBOR_FINDER.get(index);
        } catch(IndexOutOfBoundsException e) {
            throw new CAException(CAException.INVALID_SHAPE);
        }
        buildNeighborGraph();
    }
    
    /**
     * Sets all the cells in the grid to their next state
     */
    public void update() {
        for(Cell cell: this) {
            cell.update();
        }
    }
    
    /**
     * Returns the number of neighbors of a middle cell
     * @return the number of neighbors of a middle cell
     */
    public int numNeighbors() {
        return myFinder.numNeighbors(isDiagonal);
    }

    /**
     * Returns a list of all the cells with the specified state
     * @param state
     * @return a list of all the cells with the specified state
     */
    public List<Cell> getCells(CellState state){
        List<Cell> cells = new ArrayList<Cell>();
        for(Cell cell: this) {
            if (cell.is(state)){
                cells.add(cell);
            }
        }
        return cells;
    }
    
    /**
     * Returns a new grid of the specified type with the current cell configuration
     * @param type - refer to GRID_TYPE for available types
     * @return
     */
    public Grid switchGrid(String type) {
        if(type.equals(GRID_TYPE.get(0))) {
            return new FlatGrid(this);
        }
        else if(type.equals(GRID_TYPE.get(1))) {
            return new ToroidalGrid(this);
        }
        throw new CAException(CAException.INVALID_GRID, type);
    }
    
    /**
     * Sets the neighbor pattern to the specified type
     * @param type - refer to NEIGHBOR_PATTERN for the available patterns
     */
    public void setNeighborPattern(String type) {
        if(type.equals(NEIGHBOR_PATTERN.get(0))) {
            setDiagonal(true);
        }
        else if(type.equals(NEIGHBOR_PATTERN.get(1))) {
            setDiagonal(false);
        }
        throw new CAException(CAException.INVALID_GRID, type);
    }
    
    @Override
    public Iterator<Cell> iterator() {
        return new Iterator<Cell>() {

            private int row, col;
            
            @Override
            public boolean hasNext() {
                return row < numRows() && col < numCols();
            }

            @Override
            public Cell next() {
                Cell cell = get(row, col++);
                if(col >= numCols()) {
                    col = 0;
                    row++;
                }
                return cell;
            }
        };
    }
    
    private void buildNeighborGraph() {
        for(int row = 0; row < numRows(); row++) {
            for(int col = 0; col < numCols(); col++) {
                Set<Cell> neighbors = new HashSet<Cell>();
                for(Location loc: myFinder.findNeighbor(row, col, isDiagonal)) {
                    addNeighbor(loc.getRow(), loc.getCol(), neighbors);
                }
                sim[row][col].setNeighbors(neighbors);
            }
        }
    }
    
    /**
     * Determine if cell at position (row, col) should be added to neighbors
     * Helper method implemented by subclasses
     * @param row
     * @param col
     * @param neighbors
     */
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
