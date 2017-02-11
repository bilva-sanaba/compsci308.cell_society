package grid;

import java.util.Collection;

import cell.Cell;
import cell.CellConfig;
import cell.generator.CellGenerator;
import cellsociety.Grid;

public class ToroidalGrid extends Grid {

    public ToroidalGrid(int row, int col, Collection<CellConfig> cellConfig, CellGenerator generator, boolean diagonal) {
        super(row, col, cellConfig, generator, diagonal);
    }
    
    public ToroidalGrid(Grid grid) {
        super(grid);
    }

    @Override
    protected void addNeighbor(int row, int col, Collection<Cell> neighbors) {
        neighbors.add(get(Math.floorMod(row, numRows()), Math.floorMod(col, numCols())));
    }

}
