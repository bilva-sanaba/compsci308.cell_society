package grid.neighborfinder;

import java.util.Arrays;
import java.util.Collection;

import grid.Location;
import grid.NeighborOffset;

public class RectangleFinder extends NeighborFinder {
    
    public static final NeighborOffset CARDINAL = new NeighborOffset(
            Arrays.asList(-1, 0, 0, 1),
            Arrays.asList(0, -1, 1, 0));
    public static final NeighborOffset DIAGONAL = new NeighborOffset(
            Arrays.asList(-1, -1, 1, 1),
            Arrays.asList(-1, 1, -1, 1));
    
    private NeighborOffset cardinalOffset, diagonalOffset;
    
    public RectangleFinder() {
        cardinalOffset = CARDINAL;
        diagonalOffset = DIAGONAL;
    }

    @Override
    public Collection<Location> findNeighbor(int row, int col, boolean diagonal) {
        return findNeighbor(row, col, diagonal, cardinalOffset, diagonalOffset);
    }

}
