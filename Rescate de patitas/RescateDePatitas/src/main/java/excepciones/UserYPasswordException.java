package excepciones;

public class UserYPasswordException extends PasswordException{
    private static final String message = "El Usuario y la Contraseña tienen que ser distintos.";
    public UserYPasswordException() { super(message); }
}
