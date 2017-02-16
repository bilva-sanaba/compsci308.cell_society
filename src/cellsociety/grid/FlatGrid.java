// This entire file is part of my masterpiece.
// Mike Liu

package cellsociety.grid;

import java.util.Collection;

import cellsociety.cell.Cell;
import cellsociety.cell.CellConfig;
import cellsociety.cell.generator.CellGenerator;

/**
 * Flat grid with finite edge
 * @author Mike Liu
 * @author Bilva Sanaba
 */
public class FlatGrid extends Grid {

    public FlatGrid(int row, int col, Collection<CellConfig> cellConfig, CellGenerator generator) {
        super(row, col, cellConfig, generator);
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
