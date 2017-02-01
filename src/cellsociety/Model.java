package cellsociety;

public abstract class Model {
    
    private Grid<? extends Cell> grid;
    
    public Model() {
        //TODO
    }
    
    public Grid<? extends Cell> getGrid() {
        return grid;
    }
    
    public void setGrid(Grid<? extends Cell> grid) {
        this.grid = grid;
    }
    
    public abstract void step();
    
    public static Model getModel() {
        //TODO
        return null;
    }
}
