package grid;

import java.util.Arrays;
import java.util.Collection;

import cell.CellConfig;
import cell.CellGenerator;
import cellsociety.Cell;
import cellsociety.Grid;

/**
 * Grid that treats its cells as hexagons
 * @author Mike Liu
 *
 */
public class HexagonalGrid extends Grid {
    
    public static final NeighborOffset EVEN = new NeighborOffset(
            Arrays.asList(-1, -1, 0, 0, 1, 1),
            Arrays.asList(-1, 0, -1, 1, -1, 0));
    public static final NeighborOffset ODD = new NeighborOffset(
            Arrays.asList(-1, -1, 0, 0, 1, 1),
            Arrays.asList(0, 1, -1, 1, 0, 1));
    
    private NeighborOffset evenOffset, oddOffset;
    
	public HexagonalGrid(int row, int col, Collection<CellConfig> cellConfig, CellGenerator generator) {
        super(row, col, cellConfig, generator);
        evenOffset = EVEN;
        oddOffset = ODD;
        buildNeighborGraph(false);
    }
 
    @Override
    protected Collection<Cell> findNeighbor(int row, int col, boolean diagonal) {
        if(row % 2 == 0) {
            return findNeighbor(row, col, evenOffset);
        } else {
            return findNeighbor(row, col, oddOffset);
        }
    }	
}
