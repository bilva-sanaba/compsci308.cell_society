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
        double leftX = (col)/2. * width;
        double midX = (col+1)/2. * width;
        double rightX = (col/2.+1) * width;
        double topY = row * height;
        double botY = (row+1) * height;
        if((row + col) % 2 == 0) {
            return new Polygon(
                    leftX, topY,
                    rightX, topY,
                    midX, botY);
        } else {
            return new Polygon(
                    midX, topY,
                    leftX, botY,
                    rightX, botY);
        }
    }

    private double getHeight(double width) {
        return width * Math.sqrt(3) / 2;
    }
}
