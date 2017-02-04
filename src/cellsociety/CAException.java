package cellsociety;

public class CAException extends RuntimeException {
    
    public static final String INVALID_CELL = "Invalid cell state for %s model";
    public static final String INVALID_SHAPE = "InvalidShape";
    public static final String NO_MODEL = "No valid model loaded";

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

}
