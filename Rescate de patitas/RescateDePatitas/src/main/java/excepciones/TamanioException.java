package excepciones;

public class TamanioException extends RuntimeException {
    private static final String message = "El password no cumple con la cantidad de caracteres mínimos.";
    public TamanioException() {
        super(message);
    }
}
