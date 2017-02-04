package cellsociety;

import cellsociety.cellgenerator.CellGenerator;

public abstract class Model {
    
    private Grid grid;
    CellGenerator generator;
    
    public Model(CellGenerator generator) {
        this.generator = generator;
        //TODO create grid based on xml data
    }
    
    public Grid getGrid() {
        return grid;
    }
    
    protected void setGrid(Grid grid) {
        this.grid = grid;
    }
    
    public abstract void update();
    
    public static Model getModel() {
        //TODO
        return null;
    }
}
