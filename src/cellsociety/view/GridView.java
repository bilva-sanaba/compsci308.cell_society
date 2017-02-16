package cellsociety.view;

import cellsociety.CAException;
import cellsociety.handler.CellClickHandler;
import cellsociety.model.Model;
import cellsociety.view.shapegenerator.ShapeGenerator;
import cellsociety.view.shapegenerator.ShapeGeneratorFactory;
import javafx.scene.Group;

/**
 * Displays the animation of the simulation
 * Displays cells as rectangles by default
 * @author Mike Liu
 *
 */
public class GridView {
    
    public static final double ZOOM_RATE = 0.8;
    
    private Group myRoot;
    private Model myModel;
    private double gridWidth;
    private CellClickHandler myHandler;
    private ShapeGenerator myGenerator;
    
    public GridView(double width, CellClickHandler handler) {
        super();
        gridWidth = width;
        myRoot = new Group();
        myHandler = handler;
        myGenerator = new ShapeGeneratorFactory().newShapeGenerator(ShapeGeneratorFactory.SQUARE);
    }
    
    public Group getRoot() {
        return myRoot;
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
        myGenerator = new ShapeGeneratorFactory().newShapeGenerator(type);
        update();
    }
    
    /**
     * Updates the display
     */
    public void update() {
        if(myModel == null) {
            throw new CAException(CAException.NO_MODEL);
        }
        myRoot.getChildren().clear();
        myRoot.getChildren().addAll(myGenerator.generate(gridWidth, myModel.getGrid(), myHandler));
    }
}
