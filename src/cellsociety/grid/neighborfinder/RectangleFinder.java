package cellsociety.grid.neighborfinder;

import java.util.Arrays;
import java.util.Collection;

import cellsociety.grid.Location;
import cellsociety.grid.NeighborOffset;

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
    		Arrays.asList(-2, -2, -1, -1, 1, 1, 2, 2),
    		Arrays.asList(-1, 1, -2, 2, -2, 2, -1, 1));
    
    private NeighborOffset cardinalOffset, diagonalOffset;
    
    public RectangleFinder(boolean diagonal) {
        super(diagonal);
    }

    @Override
    public void toNormal() {
        cardinalOffset = CARDINAL;
        diagonalOffset = DIAGONAL;
    }
    
    public void toKnight(){
    	cardinalOffset = KNIGHT;
    	diagonalOffset = NeighborFinder.EMPTY;
    }
    
    @Override
    public Collection<Location> findNeighbor(int row, int col) {
        return findNeighbor(row, col, cardinalOffset, diagonalOffset);
    }
    
    @Override
    public int numNeighbors() {
        if(isDiagonal()) {
            return cardinalOffset.length() + diagonalOffset.length();
        } else {
            return cardinalOffset.length();
        }
    }
}
