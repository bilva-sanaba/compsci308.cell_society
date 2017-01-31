package cellsociety;

import javafx.scene.layout.Pane;

/**
 * Displays the animation of the simulation
 * @author Mike Liu
 *
 */
public class View extends Pane {
    
    private Model model;
    
    public View() {
        //TODO
    }
    
    public void setModel(Model model) {
        this.model = model;
    }
    
    public void update() {
        Grid grid = model.getGrid();
    }
}
