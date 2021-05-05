package exception;

public class PasswordInvalidException extends RuntimeException {
    private static final String message = "El password que desea almacenar el invalido";
    public PasswordInvalidException() {
        super(message);
    }
}
