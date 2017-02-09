package grid;

import java.util.Arrays;
import java.util.Collection;

import cell.CellConfig;
import cell.CellGenerator;
import cellsociety.Cell;
import cellsociety.Grid;

/**
 * Grid that treats its cells as rectangles
 * @author Mike Liu
 *
 */
public class RectangleGrid extends Grid {
    
    public static final NeighborOffset CARDINAL = new NeighborOffset(
            Arrays.asList(-1, 0, 0, 1),
            Arrays.asList(0, -1, 1, 0));
    public static final NeighborOffset DIAGONAL = new NeighborOffset(
            Arrays.asList(-1, -1, 1, 1),
            Arrays.asList(-1, 1, -1, 1));
    
	private NeighborOffset cardinalOffset = CARDINAL, diagonalOffset = DIAGONAL;
	
	public RectangleGrid(int row, int col, Collection<CellConfig> cellConfig, CellGenerator generator, boolean diagonal) {
        super(row, col, cellConfig, generator);
        cardinalOffset = CARDINAL;
        diagonalOffset = DIAGONAL;
        buildNeighborGraph(diagonal);
    }
	
    @Override
    protected Collection<Cell> findNeighbor(int row, int col, boolean diagonal) {
        Collection<Cell> neighbors = findNeighbor(row, col, cardinalOffset);
        for(int i = 0; i < cardinalOffset.length(); i++) {
        }
        if(diagonal) {
            neighbors.addAll(findNeighbor(row, col, diagonalOffset));
        }
        return neighbors;
    }
}


	
