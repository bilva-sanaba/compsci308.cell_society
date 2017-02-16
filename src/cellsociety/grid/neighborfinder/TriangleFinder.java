package cellsociety.grid.neighborfinder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cellsociety.grid.Location;
import cellsociety.grid.NeighborOffset;

/**
 * Finds the neighbors of a location given by (row, col), treating the boundary as triangle
 * @author Mike Liu
 * @author Bilva Sanaba
 */
public class TriangleFinder extends NeighborFinder {

    public static final NeighborOffset EVEN_CARDINAL = new NeighborOffset(
            Arrays.asList(-1, 0, 0),
            Arrays.asList(0, -1, 1));
    public static final NeighborOffset EVEN_FULL = new NeighborOffset(
            EVEN_CARDINAL,
            Arrays.asList(-1, -1, -1, -1, 0, 0, 1, 1, 1),
            Arrays.asList(-1, -2, 1, 2, -2, 2, -1, 0, 1));
    public static final NeighborOffset ODD_CARDINAL = new NeighborOffset(
            Arrays.asList(0, 0, 1),
            Arrays.asList(-1, 1, 0));
    public static final NeighborOffset ODD_FULL = new NeighborOffset(
            ODD_CARDINAL,
            Arrays.asList(-1, -1, -1, 0, 0, 1, 1, 1, 1),
            Arrays.asList(-1, 0, 1, -2, 2, -2, -1, 1, 2));
    public static final NeighborOffset EVEN_KNIGHT = new NeighborOffset(
            Arrays.asList(-2, -2, -1, 0, 0, -1, 1, 1, 2, 2),
            Arrays.asList(-1, 1, -3, 3, -3, 3, -2, 2, -1, 1));
    public static final NeighborOffset ODD_KNIGHT = new NeighborOffset(
            Arrays.asList(-2, -2, -1, 0, 0, -1, 1, 1, 2, 2),
            Arrays.asList(-1, 1, -2, 2, -3, 3, -3, 3, -1, 1));
    private static final List<NeighborOffset> EVEN_OFFSETS = Arrays.asList(
            EVEN_FULL,
            EVEN_CARDINAL,
            EVEN_KNIGHT);
    private static final List<NeighborOffset> ODD_OFFSETS = Arrays.asList(
            ODD_FULL,
            ODD_CARDINAL,
            ODD_KNIGHT);
    
    private NeighborOffset evenOffset, oddOffset;
    
    public TriangleFinder() {
        super();
    }
    
    @Override
    public Collection<Location> findNeighbor(int row, int col) {
        if((row + col) % 2 == 0) {
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
