package cellsociety.view;

import cellsociety.CAException;
import cellsociety.handler.CellClickHandler;
import cellsociety.model.Model;
import cellsociety.view.shapegenerator.HexagonGenerator;
import cellsociety.view.shapegenerator.ShapeGenerator;
import cellsociety.view.shapegenerator.SquareGenerator;
import cellsociety.view.shapegenerator.TriangleGenerator;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;

/**
 * Displays the animation of the simulation
 * Displays cells as rectangles by default
 * @author Mike Liu
 *
 */
public class GridView extends ScrollPane {
    
    public static final int SQUARE = 0;
    public static final int TRIANGLE = 1;
    public static final int HEXAGON = 2;
    
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
        myGenerator = getShapeGenerator(0);
    }
    
    /**
     * Zooms in the display
     */
    public void zoomIn() {
        gridWidth /= ZOOM_RATE;
        update();
    }

    /**
     * Zooms out the display
     */
    public void zoomOut() {
        gridWidth *= ZOOM_RATE;
        update();
    }
    
    /**
     * Sets the model that is displayed
     * @param model - model to be displayed
     */
    public void setModel(Model model) {
        myModel = model;
        update();
    }
    
    /**
     * Sets the shape of cells in the display
     * @param type - refer to the constants for the available types
     */
    public void setShape(int type) {
        myGenerator = getShapeGenerator(type);
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
    
    private ShapeGenerator getShapeGenerator(int type) {
        if(type == SQUARE) {
            return new SquareGenerator();
        }
        else if(type == TRIANGLE) {
            return new TriangleGenerator();
        }
        else if(type == HEXAGON) {
            return new HexagonGenerator();
        } else {
            throw new CAException(CAException.INVALID_SHAPE);
        }
    }
}
