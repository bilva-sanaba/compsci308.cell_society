package cellsociety;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import shapegenerator.ShapeGenerator;

/**
 * Displays the animation of the simulation
 * @author Mike Liu
 *
 */
public class GridView extends ScrollPane {
    
    private Model myModel;
    private ShapeGenerator myGenerator;
    
    public GridView(double width, double height) {
        super();
        setPrefSize(width, height);
    }
    
    public void setModel(Model model) {
        this.myModel = model;
    }
    
    public void setShape(ShapeGenerator generator) {
        myGenerator = generator;
    }
    
    /**
     * Updates the display
     */
    public void update() {
        getChildren().clear();
        getChildren().addAll(myGenerator.generate(getPrefWidth(), myModel.getGrid()));
    }
}
