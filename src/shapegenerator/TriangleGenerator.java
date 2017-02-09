package shapegenerator;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangleGenerator extends ShapeGenerator {

    public TriangleGenerator() {
        super();
    }

    @Override
    protected double calculateWidth(double gridWidth, int numCols) {
        return 2 * gridWidth / (numCols + 1);
    }

    @Override
    protected Shape getShape(int row, int col, double width) {
        double height = getHeight(width);
        if((row + col) % 2 == 0) {
            return new Polygon(
                    col/2. * width, row * height,
                    (col/2.+1) * width, row * height,
                    (col+1) * width/2., (row+1) * height);
        } else {
            return new Polygon(
                    (col+1) * width/2., row * height,
                    (col)/2. * width, (row+1) * height,
                    (col/2.+1) * width, (row+1) * height);
        }
    }

    private double getHeight(double width) {
        return width * Math.sqrt(3) / 2;
    }
}
