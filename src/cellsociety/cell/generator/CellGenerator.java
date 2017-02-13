package cellsociety.cell.generator;

import cellsociety.cell.Cell;

/**
 * Generates cells
 * @author Mike Liu
 *
 */
public interface CellGenerator {
    
    /**
     * Returns the basic cell for the simulation
     * @return
     */
    public Cell getBasicCell();

    /**
     * Returns the cell of the specified state
     * @param state
     * @return
     */
    public Cell getCell(int state);
}
