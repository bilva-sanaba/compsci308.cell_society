package grid;

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
}
