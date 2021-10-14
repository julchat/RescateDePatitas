package domain.model.cuenta;

import domain.model.cuenta.regalos.CDMusica;

public class CuentaJoven extends Cuenta{

    public CuentaJoven(){
        this.setIntereses(0.02);
        this.setTarjetaDebito(new TarjetaDebito(0));
        this.setTarjetaCredito(null);
        this.setRegalo(new CDMusica());
    }

    /*
    @Override
    public boolean cumpleCondicion(Cliente cliente) {
        return cliente.getEdad() <= 25;
    }*/

}
