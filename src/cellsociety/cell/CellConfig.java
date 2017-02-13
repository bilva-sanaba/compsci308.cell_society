package cellsociety.cell;

/**
 * Data class for the configuration of a single cell
 * @author Mike Liu
 *
 */
public class CellConfig {
    
    private int myRow, myCol, myState;

    public CellConfig(int row, int col, int state) {
        myRow = row;
        myCol = col;
        myState = state;
    }
    
    public int getRow() {
        return myRow;
    }
    
    public int getCol() {
        return myCol;
    }
    
    public int getState() {
        return myState;
    }
}
