package cellsociety;

public abstract class Model {
    
    public static final int FRAMES_PER_SECOND = 1;
    public static final double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    
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
