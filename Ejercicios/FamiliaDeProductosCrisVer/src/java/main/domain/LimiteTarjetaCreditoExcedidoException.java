package java.main.domain;

public class LimiteTarjetaCreditoExcedidoException extends RuntimeException{
    public LimiteTarjetaCreditoExcedidoException(){
        super("El limite de credito de la tarjeta se superaria. Operacion cancelada");
    }
}
