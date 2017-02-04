package cellsociety;

import javafx.scene.layout.Pane;
import shapegenerator.ShapeGenerator;

/**
 * Displays the animation of the simulation
 * @author Mike Liu
 *
 */
public class GridView extends Pane {
    
    private Model model;
    private ShapeGenerator generator;
    
    public GridView(double width) {
        super();
        setPrefWidth(width);
    }
    
    public void setModel(Model model) {
        this.model = model;
    }
    
    public void setShape(String shape) {
        ShapeGenerator.getShapeGenerator(shape);
    }
    
    /**
     * Updates the display
     */
    public void update() {
        getChildren().clear();
        getChildren().addAll(generator.generate(getPrefWidth(), model.getGrid()));
    }
}
