package excepciones;

public class TamanioException extends PasswordException {
    private static final String message = "La Contraseña debe tener un largo entre 8 y 64 caracteres.";
    public TamanioException() { super(message); }
}
