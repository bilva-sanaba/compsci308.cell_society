package grid;

import java.util.Collection;

import cell.Cell;
import cell.CellConfig;
import cell.generator.CellGenerator;
import cellsociety.Grid;

public class FlatGrid extends Grid {

    public FlatGrid(int row, int col, Collection<CellConfig> cellConfig, CellGenerator generator, boolean diagonal) {
        super(row, col, cellConfig, generator, diagonal);
    }
    
    public FlatGrid(Grid grid) {
        super(grid);
    }

    @Override
    protected void addNeighbor(int row, int col, Collection<Cell> neighbors) {
        if(inBounds(row, col)) {
            neighbors.add(get(row, col));
        }
    } 
    
    private boolean inBounds(int row, int col){
        return row >=0 && row < numRows() && col >= 0 && col < numCols();
    }
}
