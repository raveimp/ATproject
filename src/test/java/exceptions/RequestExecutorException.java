package exceptions;

public class RequestExecutorException extends RuntimeException {

    public RequestExecutorException(String message) {
        super(message);
    }

    public RequestExecutorException(Throwable cause) {
        super(cause);
    }

    public RequestExecutorException(String message, Throwable cause) {
        super(message, cause);
    }
}