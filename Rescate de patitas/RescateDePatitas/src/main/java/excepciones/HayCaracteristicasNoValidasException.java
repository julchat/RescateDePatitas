package excepciones;

public class HayCaracteristicasNoValidasException extends RuntimeException {
    private static final String message = "Las caracteristicas ingresadas no son validas para la organizacion";
    public HayCaracteristicasNoValidasException() {
        super(message);
    }
}
