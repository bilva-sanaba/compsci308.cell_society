package grid.neighborfinder;

import java.util.Arrays;
import java.util.Collection;

import grid.Location;
import grid.NeighborOffset;

/**
 * Finds the neighbors of a location given by (row, col), treating the boundary as hexagon
 * @author Mike Liu
 * @author Bilva Sanaba
 */
public class HexagonFinder extends NeighborFinder {
    
    public static final NeighborOffset EVEN = new NeighborOffset(
            Arrays.asList(-1, -1, 0, 0, 1, 1),
            Arrays.asList(-1, 0, -1, 1, -1, 0));
    public static final NeighborOffset ODD = new NeighborOffset(
            Arrays.asList(-1, -1, 0, 0, 1, 1),
            Arrays.asList(0, 1, -1, 1, 0, 1));
    public static final NeighborOffset KNIGHT = new NeighborOffset(
    		Arrays.asList(-2,-2,-1,-1,1,1,2,2),
    		Arrays.asList(-1,1,-2,2,-2,2,-1,1));
    
    private NeighborOffset evenOffset, oddOffset;
    
    
    public HexagonFinder() {
        evenOffset = EVEN;
        oddOffset = ODD;
    }
    public void toKnight(){
    	evenOffset = KNIGHT;
    	oddOffset = KNIGHT;
    }
    @Override
    public Collection<Location> findNeighbor(int row, int col, boolean diagonal) {
        if(row % 2 == 0) {
            return findNeighbor(row, col, evenOffset);
        } else {
            return findNeighbor(row, col, oddOffset);
        }
    }

    @Override
    public int numNeighbors(boolean diagonal) {
        return evenOffset.length();
    }

}
