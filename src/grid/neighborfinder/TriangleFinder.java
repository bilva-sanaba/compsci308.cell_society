package grid.neighborfinder;

import java.util.Arrays;
import java.util.Collection;

import grid.Location;
import grid.NeighborOffset;

/**
 * Finds the neighbors of a location given by (row, col), treating the boundary as triangle
 * @author Mike Liu
 *
 */
public class TriangleFinder extends NeighborFinder {
    
    public static final NeighborOffset EVEN_CARDINAL = new NeighborOffset(
            Arrays.asList(-1, 0, 0),
            Arrays.asList(0, -1, 1));
    public static final NeighborOffset EVEN_DIAGONAL = new NeighborOffset(
            Arrays.asList(-1, -1, -1, -1, 0, 0, 1, 1, 1),
            Arrays.asList(-1, -2, 1, 2, -2, 2, -1, 0, 1));
    public static final NeighborOffset ODD_CARDINAL = new NeighborOffset(
            Arrays.asList(0, 0, 1),
            Arrays.asList(-1, 1, 0));
    public static final NeighborOffset ODD_DIAGONAL = new NeighborOffset(
            Arrays.asList(-1, -1, -1, 0, 0, 1, 1, 1, 1),
            Arrays.asList(-1, 0, 1, -2, 2, -2, -1, 1, 2));
    
    private NeighborOffset evenCardinalOffset, evenDiagonalOffset, oddCardinalOffset, oddDiagonalOffset;
    
    public TriangleFinder() {
        evenCardinalOffset = EVEN_CARDINAL;
        evenDiagonalOffset = EVEN_DIAGONAL;
        oddCardinalOffset = ODD_CARDINAL;
        oddDiagonalOffset = ODD_DIAGONAL;
    }

    @Override
    public Collection<Location> findNeighbor(int row, int col, boolean diagonal) {
        if((row + col) % 2 == 0) {
            return findNeighbor(row, col, diagonal, evenCardinalOffset, evenDiagonalOffset);
        } else {
            return findNeighbor(row, col, diagonal, oddCardinalOffset, oddDiagonalOffset);
        }
    }

}
