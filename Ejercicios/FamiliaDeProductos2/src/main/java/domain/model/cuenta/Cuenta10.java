package domain.model.cuenta;

import domain.model.cuenta.regalos.ReproductorCD;

public class Cuenta10 extends Cuenta{

    public Cuenta10() {
        this.setIntereses(0.01);
        this.setTarjetaDebito(new TarjetaDebito(0));
        this.setTarjetaCredito(new TarjetaCredito(180, 0.60));
        this.setRegalo(new ReproductorCD());
    }

    /*
    @Override
    public boolean cumpleCondicion(Cliente cliente) {
        return this.entre(26, 65, cliente.getEdad()) && cliente.isPension();
    }

    private boolean entre(int referencia1, int referencia2, int valor) {
        return valor >= referencia1 && valor <= referencia2;
    }*/
}
