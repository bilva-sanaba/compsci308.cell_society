package cellsociety;

import cell.CellGenerator;

public abstract class Model {
    
    private Grid myGrid;
    CellGenerator myGenerator;
    
    public Model(Grid grid) {
        myGrid = grid;
    }
    
    public Grid getGrid() {
        return myGrid;
    }
    
    protected void setGrid(Grid grid) {
        this.myGrid = grid;
    }
    
    public abstract void update();
}
