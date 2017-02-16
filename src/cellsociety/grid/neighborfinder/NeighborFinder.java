package cellsociety.grid.neighborfinder;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cellsociety.CAException;
import cellsociety.grid.Location;
import cellsociety.grid.NeighborOffset;

/**
 * Finds the neighbors of a location given by (row, col)
 * @author Mike Liu
 *
 */
public abstract class NeighborFinder {
    
    public static final List<String> NEIGHBOR_PATTERN = Arrays.asList(
            "Full",
            "Cardinal",
            "Knight");
    public static final int FULL = 0;
    public static final int CARDINAL = 1;
    public static final int KNIGHT = 2;
    
    private int myPattern;
    
    public NeighborFinder() {
        setNeighborPattern(0);
    }

    /**
     * Returns the neighbors (in the form of a collection of locations) of the cell at (row, col)
     * @param row
     * @param col
     * @param diagonal - whether diagonal neighbors are included
     * @return
     */
    public abstract Collection<Location> findNeighbor(int row, int col);
    
    /**
     * Returns the number of neighbors considered
     * @param diagonal - whether diagonal neighbors are included
     * @return
     */
    public abstract int numNeighbors();
    
    public void setNeighborPattern(int type) {
        if(type < 0 || type >= NEIGHBOR_PATTERN.size()) {
            throw new CAException(CAException.INVALID_GRID, NEIGHBOR_PATTERN.get(type));
        }
        myPattern = type;
        setOffset(type);
    }

    public int getPattern() {
        return myPattern;
    }
    
    protected abstract void setOffset(int type);
    
    /**
     * Returns the neighbors of cell at (row, col) according to the given offset
     * Helper method commonly needed by subclasses
     * @param row
     * @param col
     * @param offset
     * @return
     */
    protected Collection<Location> findNeighbor(int row, int col, NeighborOffset offset) {
        Set<Location> neighbors = new HashSet<Location>();
        for(int i = 0; i < offset.length(); i++) {
            neighbors.add(new Location(row + offset.getRowOffset(i), col + offset.getColOffset(i)));
        }
        return neighbors;
    }
}
