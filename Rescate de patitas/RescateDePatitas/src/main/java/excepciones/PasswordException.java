package excepciones;

public abstract class PasswordException extends RuntimeException {
    public PasswordException(String message) { super(message); }
}
