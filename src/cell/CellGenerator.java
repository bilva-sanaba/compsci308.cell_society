package cell;

import cellsociety.Cell;

public interface CellGenerator {
    
    public Cell getBasicCell();

    public Cell getCell(int state);
}
