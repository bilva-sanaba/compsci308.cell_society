package cellsociety.grid.neighborfinder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
    public static final NeighborOffset FULL = new NeighborOffset(
            CARDINAL,
            Arrays.asList(-1, -1, 1, 1),
            Arrays.asList(-1, 1, -1, 1));
    public static final NeighborOffset KNIGHT = new NeighborOffset(
    		Arrays.asList(-2, -2, -1, -1, 1, 1, 2, 2),
    		Arrays.asList(-1, 1, -2, 2, -2, 2, -1, 1));
    private static final List<NeighborOffset> OFFSETS = Arrays.asList(
            FULL,
            CARDINAL,
            KNIGHT);
    
    private NeighborOffset myOffset;
    
    public RectangleFinder() {
        super();
    }
    
    @Override
    public Collection<Location> findNeighbor(int row, int col) {
        return findNeighbor(row, col, myOffset);
    }
    
    @Override
    public int numNeighbors() {
        return myOffset.length();
    }

    @Override
    protected void setOffset(int type) {
        myOffset = OFFSETS.get(0);
    }
}
