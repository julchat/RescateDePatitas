package excepciones;

public class PasswordNumero extends PasswordException{
    private static final String message = "La Contraseña necesita al menos un número.";
    public PasswordNumero() { super(message); }
}
