package exception;

public class AddBookException extends Exception {

    private static final long serialVersionUID = 1L;

    public AddBookException() {
        super();
    }

    public AddBookException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddBookException(String message) {
        super(message);
    }

    public AddBookException(Throwable cause) {
        super(cause);
    }

}
