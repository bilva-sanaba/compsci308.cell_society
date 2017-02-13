package cellsociety.view.shapegenerator;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Generates squares according to the grid given to fill in a space with certain width
 * @author Mike Liu
 *
 */
public class SquareGenerator extends ShapeGenerator {

    public SquareGenerator() {
        super();
    }

    @Override
    protected double calculateWidth(double gridWidth, int numCols) {
        return gridWidth/numCols;
    }

    @Override
    protected Shape getShape(int row, int col, double width) {
        Rectangle ret = new Rectangle(col*width, row*width, width, width);
        return ret;
    }

}
