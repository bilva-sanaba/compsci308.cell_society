package grid.neighborfinder;

import java.util.Arrays;
import java.util.Collection;

import grid.Location;
import grid.NeighborOffset;

/**
 * Finds the neighbors of a location given by (row, col), treating the boundary as rectangle
 * @author Mike Liu
 * @author Bilva Sanaba
 */
public class RectangleFinder extends NeighborFinder {
    
    public static final NeighborOffset CARDINAL = new NeighborOffset(
            Arrays.asList(-1, 0, 0, 1),
            Arrays.asList(0, -1, 1, 0));
    public static final NeighborOffset DIAGONAL = new NeighborOffset(
            Arrays.asList(-1, -1, 1, 1),
            Arrays.asList(-1, 1, -1, 1));
    public static final NeighborOffset KNIGHT = new NeighborOffset(
    		Arrays.asList(-2,-2,-1,-1,1,1,2,2),
    		Arrays.asList(-1,1,-2,2,-2,2,-1,1));
    private NeighborOffset cardinalOffset, diagonalOffset;
    
    public RectangleFinder() {
        cardinalOffset = CARDINAL;
        diagonalOffset = DIAGONAL;
    }
    public void toKnight(){
    	cardinalOffset = KNIGHT;
    	diagonalOffset = KNIGHT;
    }
    @Override
    public Collection<Location> findNeighbor(int row, int col, boolean diagonal) {
        return findNeighbor(row, col, diagonal, cardinalOffset, diagonalOffset);
    }
    
    @Override
    public int numNeighbors(boolean diagonal) {
        if(diagonal) {
            return cardinalOffset.length() + diagonalOffset.length();
        } else {
            return cardinalOffset.length();
        }
    }
}
