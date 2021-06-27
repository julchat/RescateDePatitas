package excepciones;

public class CaracteristicasNoValidasException extends RuntimeException {
    private static final String message = "Las caracteristicas ingresadas no son validas para la organizacion";
    public NullException() {
        super(message);
    }
}