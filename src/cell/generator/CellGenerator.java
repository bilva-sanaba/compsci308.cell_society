package cell.generator;

import cell.Cell;

public interface CellGenerator {
    
    public Cell getBasicCell();

    public Cell getCell(int state);
}
