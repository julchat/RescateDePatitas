package domain.exception;

public class CuentaNoEncontrada extends RuntimeException {
    private static final String message = "El Cliente no tiene la cuenta buscada";
    public CuentaNoEncontrada() {
        super(message);
    }
}


