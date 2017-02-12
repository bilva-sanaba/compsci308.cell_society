package cellsociety;

import cellsociety.handler.CellClickHandler;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import shapegenerator.ShapeGenerator;

/**
 * Displays the animation of the simulation
 * Displays cells as rectangles by default
 * @author Mike Liu
 *
 */
public class GridView extends ScrollPane {
    
    public static final double ZOOM_RATE = 0.8;
    
    private Group root;
    private Model myModel;
    private double gridWidth;
    private CellClickHandler myHandler;
    private ShapeGenerator myGenerator;
    
    public GridView(double width, CellClickHandler handler) {
        super();
        setPrefWidth(width);
        gridWidth = width;
        root = new Group();
        setContent(root);
        myHandler = handler;
        myGenerator = ShapeGenerator.shapeGenerator(0);
    }
    
    public void zoomIn() {
        gridWidth /= ZOOM_RATE;
        update();
    }
    
    public void zoomOut() {
        gridWidth *= ZOOM_RATE;
        update();
    }
    
    public void setModel(Model model) {
        myModel = model;
        update();
    }
    
    public void setShape(int type) {
        myGenerator = ShapeGenerator.shapeGenerator(type);
        update();
    }
    
    /**
     * Updates the display
     */
    public void update() {
        if(myModel == null) {
            throw new CAException(CAException.NO_MODEL);
        }
        root.getChildren().clear();
        root.getChildren().addAll(myGenerator.generate(gridWidth, myModel.getGrid(), myHandler));
    }
}
