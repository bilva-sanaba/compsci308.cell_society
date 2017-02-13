package cellsociety.grid.neighborfinder;

import java.util.ArrayList;
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
    
    private boolean isDiagonal;
    private int myPattern;
    
    public NeighborFinder(boolean diagonal) {
        toNormal();
        isDiagonal = diagonal;
    }
    
    public static final NeighborOffset EMPTY = new NeighborOffset(
            new ArrayList<Integer>(),
            new ArrayList<Integer>());

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
        switch(type) {
        case 0: 
            toNormal();
            isDiagonal = true;
            break;
        case 1: 
            toNormal();
            isDiagonal = false;
            break;
        case 2: 
            toKnight();
            break;
        default:
            throw new CAException(CAException.INVALID_GRID, NEIGHBOR_PATTERN.get(type));
        }
        myPattern = type;
    }
    
    public int getPattern() {
        return myPattern;
    }
    
    public abstract void toNormal();
    
    public abstract void toKnight();
    
    protected boolean isDiagonal() {
        return isDiagonal;
    }
    
    /**
     * Returns the neighbors of cell at (row, col) according to the given offsets
     * Helper method commonly needed by subclasses
     * @param row
     * @param col
     * @param diagonal - whether diagonal neighbors are included
     * @param cardinalOffset
     * @param diagonalOffset
     * @return
     */
    protected Collection<Location> findNeighbor(int row, int col,
            NeighborOffset cardinalOffset, NeighborOffset diagonalOffset) {
        Collection<Location> neighbors = findNeighbor(row, col, cardinalOffset);
        if(isDiagonal()) {
            neighbors.addAll(findNeighbor(row, col, diagonalOffset));
        }
        return neighbors;
    }
    
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
