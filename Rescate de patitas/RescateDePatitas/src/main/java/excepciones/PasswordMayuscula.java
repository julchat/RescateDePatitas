package excepciones;

public class PasswordMayuscula extends PasswordException{
    private static final String message = "La Contraseña necesita al menos una mayúscula.";
    public PasswordMayuscula() { super(message); }
}
