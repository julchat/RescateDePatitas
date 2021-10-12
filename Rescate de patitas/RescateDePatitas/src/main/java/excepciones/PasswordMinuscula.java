package excepciones;

public class PasswordMinuscula extends PasswordException{
    private static final String message = "La Contraseña necesita al menos una minúscula.";
    public PasswordMinuscula() { super(message); }
}
