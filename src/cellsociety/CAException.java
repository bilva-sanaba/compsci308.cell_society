package cellsociety;

public class CAException extends RuntimeException {

    public static final String INVALID_MODEL = "The simulation %s is not defined";
    public static final String INVALID_GRID = "%s grid is not defined";
    public static final String INVALID_CELL_CONFIG = "Invalid cell configuration format";
    public static final String INVALID_CELL_NUM = "Total number of cells exceeds grid size";
    public static final String INVALID_CELL_PROB = "Total probability exceeds one";
    public static final String INVALID_CELL = "Invalid cell state for %s model";
    public static final String INVALID_SHAPE = "Invalid shape";
    public static final String INVALID_LOCATION = "Coordinate must be positive and in the grid";
    
    public static final String NO_MODEL = "No valid model loaded";
    public static final String MISSING_FIELD = "%s field is missing";
    public static final String LOAD_FAILURE = "ERROR reading file %s";
    public static final String WRONG_TYPE = "File does not represent %s";
    public static final String MISMATCH_OFFSET = "Row and column offset must have same number of elements";

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public CAException(String message) {
        super(message);
    }
    
    public CAException(String message, Object ... values) {
        super(String.format(message, values));
    }
    
    public CAException (Throwable cause) {
        super(cause);
    }
}
