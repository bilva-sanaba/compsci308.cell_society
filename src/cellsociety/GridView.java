package cellsociety;

import javafx.scene.layout.Pane;

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
    
    public void setShape(ShapeGenerator generator) {
        this.generator = generator;
    }
    
    /**
     * Updates the display
     */
    public void update() {
        getChildren().clear();
        getChildren().addAll(generator.generate(getPrefWidth(), model.getGrid()));
    }
}
