package cellsociety;

import java.util.Iterator;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Generates shapes according to the grid given to fill in a space with certain width
 * @author Mike Liu
 *
 */
public abstract class ShapeGenerator implements Iterable<Shape> {
    
    private double cellWidth;
    private Grid<? extends Cell> grid;
    
    public ShapeGenerator(double gridWidth, Grid<? extends Cell> grid) {
        cellWidth = gridWidth / grid.numCols(0);
        this.grid = grid;
    }
    
    /**
     * Returns the width of a single shape
     * Should only be called by subclasses
     * @return the width of a single shape
     */
    protected double getWidth() {
        return cellWidth;
    }

    @Override
    public Iterator<Shape> iterator() {
        return new Iterator<Shape>() {

            private int row = 0;
            private int col = 0;
            
            @Override
            public boolean hasNext() {
                return row < grid.numRows() || col < grid.numCols(row);
            }

            @Override
            public Shape next() {
                Shape shape = getShape(row, col, grid.get(row, col).getColor());
                if(col < grid.numCols(row)-1) {
                    col++;
                } else {
                    row++;
                    col = 0;
                }
                return shape;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    /**
     * Returns a shape that will be placed at (row, col) in the space
     * @param row - the row that the shape will be placed in
     * @param col - the column that the shape will be placed in
     * @param color - the color of the shape
     * @return the shape that is produced for the given row, col and color
     */
    protected abstract Shape getShape(int row, int col, Color color);
}
