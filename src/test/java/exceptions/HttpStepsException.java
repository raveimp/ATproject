package exceptions;

public class HttpStepsException extends RuntimeException {
    public HttpStepsException(String message) {
        super(message);
    }
    public HttpStepsException(Throwable cause) {
        super(cause);
    }
    public HttpStepsException(String message, Throwable cause) {
        super(message, cause);
    }
}