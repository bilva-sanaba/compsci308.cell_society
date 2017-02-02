package cellsociety;

public abstract class Model {
    
    private Grid<? extends Cell> grid;
    
    public Model() {
        //create grid based on xml data
    }
    
    public Grid<? extends Cell> getGrid() {
        return grid;
    }
    
    public void setGrid(Grid<? extends Cell> grid) {
        //TODO
    }
    
    public abstract void step();
    
    public static Model getModel() {
        //TODO
        return null;
    }
}
