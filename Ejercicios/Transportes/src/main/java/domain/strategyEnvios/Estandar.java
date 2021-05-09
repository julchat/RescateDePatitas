package domain.strategyEnvios;

import domain.Envio;

public class Estandar implements StrategyEnvio {
    private int tarifaXkgLocal;
    private int tarifaXkgDistante;

    // Getters and Setters
    public int getTarifaXkgLocal() {
        return tarifaXkgLocal;
    }

    public void setTarifaXkgLocal(int tarifaXkgLocal) {
        this.tarifaXkgLocal = tarifaXkgLocal;
    }

    public int getTarifaXkgDistante() {
        return tarifaXkgDistante;
    }

    public void setTarifaXkgDistante(int tarifaXkgDistante) {
        this.tarifaXkgDistante = tarifaXkgDistante;
    }

    // Constructor
    public Estandar(int tarifaXkgLocal, int tarifaXkgDistante) {
        this.tarifaXkgLocal = tarifaXkgLocal;
        this.tarifaXkgDistante = tarifaXkgDistante;
    }


    // Método CalcularPrecio
    @Override
    public int calcularPrecio(Envio envio) {
        if(this.esLocal(envio)) {
            return tarifaXkgLocal * envio.getPaquete().getPesoPaquete();
        }
        else {
            return tarifaXkgDistante * envio.getPaquete().getPesoPaquete();
        }
    }

    public boolean esLocal(Envio envio) {
        return envio.getDireccionDesde().mismaLocalidad(envio.getDireccionHasta());
    }
}
