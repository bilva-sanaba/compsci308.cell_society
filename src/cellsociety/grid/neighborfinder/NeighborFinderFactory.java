package cellsociety.grid.neighborfinder;

import cellsociety.CAException;

public class NeighborFinderFactory {

    public static final int RECTANGLE = 0;
    public static final int TRIANGLE = 1;
    public static final int HEXAGON = 2;
    
    public NeighborFinder newNeighborFinder(int type) {
        switch(type) {
        case RECTANGLE:
            return new RectangleFinder();
        case TRIANGLE:
            return new TriangleFinder();
        case HEXAGON:
            return new HexagonFinder();
        default:
            throw new CAException(CAException.INVALID_SHAPE);
        }
    }
}
