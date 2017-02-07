package shapegenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cellsociety.Grid;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Generates shapes according to the grid given to fill in a space with certain width
 * @author Mike Liu
 *
 */
public abstract class ShapeGenerator {
    
//    public static final String SQUARE = "square";
//    
//    public static ShapeGenerator getShapeGenerator(String shape) {
//        if(shape.toLowerCase().equals(SQUARE)) {
//            return new SquareGenerator();
//        }
//        throw new CAException(CAException.INVALID_SHAPE);
//    }
    
    public Collection<Shape> generate(double gridWidth, Grid grid) {
        List<Shape> shapes = new ArrayList<Shape>();
        double width = gridWidth/grid.numCols();
        for(int row = 0; row < grid.numRows(); row++) {
            for(int col = 0; col < grid.numCols(); col++) {
                shapes.add(getShape(row, col, width, grid.get(row, col).getColor()));
            }
        }
        return shapes;
    }
    
    /**
     * Returns a shape that will be placed at (row, col) in the space
     * @param row - the row that the shape will be placed in
     * @param col - the column that the shape will be placed in
     * @param color - the color of the shape
     * @return the shape that is produced for the given row, col and color
     */
    protected abstract Shape getShape(int row, int col, double width, Color color);
}
