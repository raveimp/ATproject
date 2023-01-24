package packages.exceptions;

public class AtFileUtilException extends GeneralException {
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