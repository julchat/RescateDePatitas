package domain.model.cuenta;

import domain.model.cuenta.regalos.Seguro;

public class CuentaOro extends Cuenta {

    public CuentaOro() {
        this.setIntereses(0.015);
        this.setTarjetaDebito(new TarjetaDebito(0));
        this.setTarjetaCredito(new TarjetaCredito(0, 0.60));
        this.setRegalo(new Seguro());
    }
/*
    @Override
    public boolean cumpleCondicion(Cliente cliente) {
        return cliente.getEdad() > 65 && cliente.isPension();
    }*/
}
