package exception;

public class NullException extends RuntimeException {
    private static final String message = "No puede ingresar una password nula.";
    public NullException() {
        super(message);
    }
}