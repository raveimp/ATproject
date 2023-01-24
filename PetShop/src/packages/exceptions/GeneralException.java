package packages.exceptions;
public class GeneralException extends RuntimeException {
    public GeneralException(String message) {
        super(message);
    }
    public GeneralException(Throwable cause) {
        super(cause);
    }
    public GeneralException(String message, String cause) {
        super(message, cause);
    }
}