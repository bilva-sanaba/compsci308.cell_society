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
    
    public static final Color STROKE = Color.BLACK;
    public static final Color ALTERNATIVE_STROKE = Color.WHITE;
    
    public Collection<Shape> generate(double gridWidth, Grid grid) {
        List<Shape> shapes = new ArrayList<Shape>();
        double width = calculateWidth(gridWidth, grid.numCols());
        for(int row = 0; row < grid.numRows(); row++) {
            for(int col = 0; col < grid.numCols(); col++) {
                Shape shape = getShape(row, col, width);
                Color color = grid.get(row, col).getColor();
                shape.setStroke(color==ShapeGenerator.STROKE
                        ? ShapeGenerator.ALTERNATIVE_STROKE : ShapeGenerator.STROKE);
                shape.setFill(color);
                shapes.add(shape);
            }
        }
        return shapes;
    }
    
    protected abstract double calculateWidth(double gridWidth, int numCols);

    /**
     * Returns a shape that will be placed at (row, col) in the space
     * @param row - the row that the shape will be placed in
     * @param col - the column that the shape will be placed in
     * @param color - the color of the shape
     * @return the shape that is produced for the given row, col and color
     */
    protected abstract Shape getShape(int row, int col, double width);
}
