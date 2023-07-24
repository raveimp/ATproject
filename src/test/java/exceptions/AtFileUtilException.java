package exceptions;

public class AtFileUtilException extends RuntimeException {

    public AtFileUtilException(String message) {
        super(message);
    }

    public AtFileUtilException(Throwable cause) {
        super(cause);
    }

    public AtFileUtilException(String message, Throwable cause) {
        super(message, cause);
    }
}