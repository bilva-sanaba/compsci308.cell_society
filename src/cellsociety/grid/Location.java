package cellsociety.grid;

/**
 * Data class for a location
 * @author Mike Liu
 *
 */
public class Location {

    private int myRow, myCol;
    
    public Location(int row, int col) {
        myRow = row;
        myCol = col;
    }
    
    public int getRow() {
        return myRow;
    }
    
    public int getCol() {
        return myCol;
    }
    
    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof Location)) {
            return false;
        }
        return myRow == ((Location)other).myRow && myCol == ((Location)other).myCol;
    }
}
