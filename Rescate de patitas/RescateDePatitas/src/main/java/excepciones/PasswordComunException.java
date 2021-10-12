package excepciones;

public class PasswordComunException extends PasswordException{
    private static final String message = "La Contraseña es muy simple y común.";
    public PasswordComunException() { super(message); }
}
