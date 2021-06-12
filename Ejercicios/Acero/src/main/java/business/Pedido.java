package business;

import java.time.LocalDate;
import java.util.List;

public class Pedido {
    private int codPedido;
    private int codCliente;
    private List<Entrega> entregas;
    //private LocalDate fechaInicioEntrega;
    private IntervaloRepeticion repe;

    // Getters and Setters
    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public List<Entrega> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<Entrega> entregas) {
        this.entregas = entregas;
    }

    public IntervaloRepeticion getRepe() {
        return repe;
    }

    public void setRepe(IntervaloRepeticion repe) {
        this.repe = repe;
    }


    // Constructor
    public Pedido(){}

    public Pedido(int codPedido, int codCliente, List<Entrega> entregas, IntervaloRepeticion repe) {
        this.codPedido = codPedido;
        this.codCliente = codCliente;
        this.entregas = entregas;
        this.repe = repe;
    }

    // Métodos
    public void generarProximaRepeticion(){}
}
