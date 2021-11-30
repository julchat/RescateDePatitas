package domain;

public class Estandar extends TipoCuenta{

    public void agregarTarjetaDebito(Cuenta cuenta){
        cuenta.deducirSaldo(50);
        this.tarjetaDebito = new TarjetaDebito(cuenta);
    }
}
