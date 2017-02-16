package cellsociety.grid.neighborfinder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cellsociety.grid.Location;
import cellsociety.grid.NeighborOffset;

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
    public static final NeighborOffset KNIGHT_EVEN = new NeighborOffset(
    		Arrays.asList(-2, -1, -1, 1, 1, 2),
    		Arrays.asList(0, -2, 1, -2, 1, 0));
    public static final NeighborOffset KNIGHT_ODD = new NeighborOffset(
            Arrays.asList(-2, -1, -1, 1, 1, 2),
            Arrays.asList(0, -1, 2, -1, 2, 0));
    private static final List<NeighborOffset> EVEN_OFFSETS = Arrays.asList(
            EVEN,
            EVEN,
            KNIGHT_EVEN);
    private static final List<NeighborOffset> ODD_OFFSETS = Arrays.asList(
            ODD,
            ODD,
            KNIGHT_ODD);
    
    private NeighborOffset evenOffset, oddOffset;
    
    public HexagonFinder() {
        super();
    }
    
    @Override
    public Collection<Location> findNeighbor(int row, int col) {
        if(row % 2 == 0) {
            return findNeighbor(row, col, evenOffset);
        } else {
            return findNeighbor(row, col, oddOffset);
        }
    }

    @Override
    public int numNeighbors() {
        return evenOffset.length();
    }

    @Override
    protected void setOffset(int type) {
        evenOffset = EVEN_OFFSETS.get(type);
        oddOffset = ODD_OFFSETS.get(type);
    }

}
