package exceptions;

public class ValueGeneratorException extends RuntimeException {
    public ValueGeneratorException(String message) {
        super(message);
    }
    public ValueGeneratorException(Throwable cause) {
        super(cause);
    }
    public ValueGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}