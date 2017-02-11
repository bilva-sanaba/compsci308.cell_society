package grid.neighborfinder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import grid.Location;
import grid.NeighborOffset;

public abstract class NeighborFinder {

    public abstract Collection<Location> findNeighbor(int row, int col, boolean diagonal);
    
    protected Collection<Location> findNeighbor(int row, int col, boolean diagonal,
            NeighborOffset cardinalOffset, NeighborOffset diagonalOffset) {
        Collection<Location> neighbors = findNeighbor(row, col, cardinalOffset);
        if(diagonal) {
            neighbors.addAll(findNeighbor(row, col, diagonalOffset));
        }
        return neighbors;
    }
    
    protected Collection<Location> findNeighbor(int row, int col, NeighborOffset offset) {
        Set<Location> neighbors = new HashSet<Location>();
        for(int i = 0; i < offset.length(); i++) {
            neighbors.add(new Location(row + offset.getRowOffset(i), col + offset.getColOffset(i)));
        }
        return neighbors;
    }
}
