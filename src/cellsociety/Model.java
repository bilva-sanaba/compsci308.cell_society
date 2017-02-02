package cellsociety;

public abstract class Model {
    
    public static final int FRAMES_PER_SECOND = 1;
    public static final double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    
    private Grid grid;
    
    public Model() {
        //create grid based on xml data
    }
    
    public Grid getGrid() {
        return grid;
    }
    
    public abstract void step();
    
    public static Model getModel() {
        return null;
    }
}
