package cellsociety;

import cell.CellGenerator;

public abstract class Model {
    
    private Grid myGrid;
    CellGenerator myGenerator;
    
    public Model(Grid grid) {
        myGrid = grid;
        //TODO create grid based on xml data
    }
    
    public Grid getGrid() {
        return myGrid;
    }
    
    protected void setGrid(Grid grid) {
        this.myGrid = grid;
    }
    
    public abstract void update();
    
    public static Model getModel() {
        //TODO
        return null;
    }
}
