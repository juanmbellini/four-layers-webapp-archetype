package ${package}.interfaces.exceptions;

/**
 * A custom exception
 */
public class CustomException extends RuntimeException {

    public CustomException() {
        super();
    }

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException(Exception cause) {
        super(cause);
    }
}