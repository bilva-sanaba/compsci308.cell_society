package shapegenerator;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class HexagonGenerator extends ShapeGenerator {

    @Override
    protected double calculateWidth(double gridWidth, int numCols) {
        return gridWidth / (numCols + 1/2.);
    }

    @Override
    protected Shape getShape(int row, int col, double width) {
        double side = getSide(width);
        double leftX = width * col;
        if(row % 2 == 1) {
            leftX += width / 2.;
        }
        double midX = leftX + width/2.;
        double rightX = leftX + width;
        double topY = side * 1.5 * row;
        double hiY = topY + 1/2. * side;
        double loY = hiY + side;
        double botY = topY + 2 * side;
        return new Polygon(
                midX, topY,
                leftX, hiY,
                leftX, loY,
                midX, botY,
                rightX, loY,
                rightX, hiY);
    }

    private double getSide(double width) {
        return width / Math.sqrt(3);
    }
}
