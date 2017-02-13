package grid.neighborfinder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import grid.Location;
import grid.NeighborOffset;

/**
 * Finds the neighbors of a location given by (row, col)
 * @author Mike Liu
 *
 */
public abstract class NeighborFinder {

    /**
     * Returns the neighbors (in the form of a collection of locations) of the cell at (row, col)
     * @param row
     * @param col
     * @param diagonal - whether diagonal neighbors are included
     * @return
     */
    public abstract Collection<Location> findNeighbor(int row, int col, boolean diagonal);
    
    /**
     * Returns the number of neighbors considered
     * @param diagonal - whether diagonal neighbors are included
     * @return
     */
    public abstract int numNeighbors(boolean diagonal);
    
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
    protected Collection<Location> findNeighbor(int row, int col, boolean diagonal,
            NeighborOffset cardinalOffset, NeighborOffset diagonalOffset) {
        Collection<Location> neighbors = findNeighbor(row, col, cardinalOffset);
        if(diagonal) {
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
