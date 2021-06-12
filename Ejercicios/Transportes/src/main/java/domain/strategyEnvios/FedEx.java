package domain.strategyEnvios;

import domain.Envio;

public class FedEx implements StrategyEnvio {
    private int tarifaXkg;

    // Getters and Setters
    public int getTarifaXkg() {
        return tarifaXkg;
    }

    public void setTarifaXkg(int tarifaXkg) {
        this.tarifaXkg = tarifaXkg;
    }

    // Constructor
    public FedEx(int tarifaXkg) {
        this.tarifaXkg = tarifaXkg;
    }

    // Método CalcularPrecio
    @Override
    public int calcularPrecio(Envio envio) {
        return tarifaXkg * envio.getPaquete().getPesoPaquete();
    }
}
