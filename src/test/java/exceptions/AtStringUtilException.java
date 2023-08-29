package exceptions;

public class AtStringUtilException extends RuntimeException {

    public AtStringUtilException(String message) {
        super(message);
    }

    public AtStringUtilException(Throwable cause) {
        super(cause);
    }

    public AtStringUtilException(String message, Throwable cause) {
        super(message, cause);
    }
}