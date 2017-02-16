package cellsociety.view.shapegenerator;

import cellsociety.CAException;

public class ShapeGeneratorFactory {
    
    public static final int SQUARE = 0;
    public static final int TRIANGLE = 1;
    public static final int HEXAGON = 2;
    
    public ShapeGenerator newShapeGenerator(int type) {
        switch(type) {
        case SQUARE:
            return new SquareGenerator();
        case TRIANGLE:
            return new TriangleGenerator();
        case HEXAGON:
            return new HexagonGenerator();
        default:
            throw new CAException(CAException.INVALID_SHAPE);
        }
    }

}
