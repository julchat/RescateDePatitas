package domain.model.cuenta;

import domain.model.Cliente;

public class CuentaEstandar extends Cuenta {

    public CuentaEstandar() {
        this.setIntereses(0.005);
        this.setTarjetaDebito(new TarjetaDebito(-50));
        this.setTarjetaCredito(null);
        this.setRegalo(null);
    }

    /*
    @Override
    public boolean cumpleCondicion(Cliente cliente) {
        return true;
    }*/
}
