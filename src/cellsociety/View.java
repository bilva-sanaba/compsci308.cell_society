package cellsociety;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 * Displays the animation of the simulation
 * @author Mike Liu
 *
 */
public class View extends Pane {
    
    private Model model;
    
    public View() {
        super();
    }
    
    public void setModel(Model model) {
        this.model = model;
    }
    
    /**
     * Updates the display
     */
    public void update() {
        getChildren().clear();
        ShapeGenerator generator = model.getGrid().getShapeGenerator(getPrefWidth());
        for(Shape shape: generator) {
            getChildren().add(shape);
        }
    }
}
