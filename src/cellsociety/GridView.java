package cellsociety;

import javafx.scene.layout.Pane;

/**
 * Displays the animation of the simulation
 * @author Mike Liu
 *
 */
public class GridView extends Pane {
    
    private Model model;
    
    public GridView(double width) {
        super();
        setPrefWidth(width);
    }
    
    public void setModel(Model model) {
        this.model = model;
    }
    
    /**
     * Updates the display
     */
    public void update() {
        getChildren().clear();
//        ShapeGenerator generator = model.getGrid().getShapeGenerator(getPrefWidth());
//        for(Shape shape: generator) {
//            getChildren().add(shape);
//        }
    }
}
