package domain.strategyEnvios;

import domain.Envio;

public class Ups implements StrategyEnvio {
    private int tarifaXkg;
    private int comision;

    // Getters and Setters
    public int getTarifaXkg() {
        return tarifaXkg;
    }

    public void setTarifaXkg(int tarifaXkg) {
        this.tarifaXkg = tarifaXkg;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    // Constructor
    public Ups(int tarifaXkg, int comision) {
        this.tarifaXkg = tarifaXkg;
        this.comision = comision;
    }

    // Método CalcularPrecio
    @Override
    public int calcularPrecio(Envio envio){
        return comision + tarifaXkg * envio.getPaquete().getPesoPaquete();
    }
}
