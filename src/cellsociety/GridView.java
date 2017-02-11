package cellsociety;

import java.util.Arrays;
import java.util.List;

import cellsociety.handler.CellClickHandler;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import shapegenerator.HexagonGenerator;
import shapegenerator.ShapeGenerator;
import shapegenerator.SquareGenerator;
import shapegenerator.TriangleGenerator;

/**
 * Displays the animation of the simulation
 * Displays cells as rectangles by default
 * @author Mike Liu
 *
 */
public class GridView extends ScrollPane {
    
    public static final List<ShapeGenerator> SHAPE_GENERATOR = Arrays.asList(
            new SquareGenerator(),
            new TriangleGenerator(),
            new HexagonGenerator());
    
    private Group root;
    private Model myModel;
    private CellClickHandler myHandler;
    private ShapeGenerator myGenerator;
    
    public GridView(double width, CellClickHandler handler) {
        super();
        setPrefWidth(width);
        root = new Group();
        setContent(root);
        myHandler = handler;
        myGenerator = SHAPE_GENERATOR.get(0);
    }
    
    public void setModel(Model model) {
        this.myModel = model;
    }
    
    public void setShape(int index) {
        try {
            myGenerator = SHAPE_GENERATOR.get(index);
        } catch(IndexOutOfBoundsException e) {
            throw new CAException(CAException.INVALID_SHAPE);
        }
        update();
    }
    
    /**
     * Updates the display
     */
    public void update() {
        root.getChildren().clear();
        root.getChildren().addAll(myGenerator.generate(getPrefWidth(), myModel.getGrid(), myHandler));
    }
}
