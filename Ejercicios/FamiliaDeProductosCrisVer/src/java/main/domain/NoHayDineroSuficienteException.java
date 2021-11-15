package java.main.domain;

public class NoHayDineroSuficienteException extends RuntimeException {
    public NoHayDineroSuficienteException(){
        super("Operacion cancelada debido a fondos insuficientes");
    }
}
