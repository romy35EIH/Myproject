package exception;

public class FindBookException extends Exception {

    private static final long serialVersionUID = 1L;

    public FindBookException() {
        super();
    }

    public FindBookException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindBookException(String message) {
        super(message);
    }

    public FindBookException(Throwable cause) {
        super(cause);
    }

}
