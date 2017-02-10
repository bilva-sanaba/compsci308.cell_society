package util;
/**
 * This class represents what might go wrong when using XML files.
 * 
 * @author Robert C. Duvall
 */
public class XMLException extends RuntimeException {
    

    public static final String LOAD_FAILURE = "ERROR reading file %s";
    public static final String WRONG_TYPE = "File does not represent %s";

    private static final long serialVersionUID = 1L;
    
    public XMLException(String message) {
        super(message);
    }

    /**
     * Create an exception based on an issue in our code.
     */
    public XMLException (String message, String value) {
        super(String.format(message, value));
    }

    /**
     * Create an exception based on a caught exception with a different message.
     */
    public XMLException (Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public XMLException (Throwable cause) {
        super(cause);
    }
}
