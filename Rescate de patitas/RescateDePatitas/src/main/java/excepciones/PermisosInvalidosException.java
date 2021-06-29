package excepciones;

public class PermisosInvalidosException extends RuntimeException {
    private static final String message = "No tiene permisos para realizar esa operacion";
    public PermisosInvalidosException() { super(message); }
}