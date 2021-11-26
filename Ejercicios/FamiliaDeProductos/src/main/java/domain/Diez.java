package domain;

public class Diez extends TipoCuenta{
    TarjetaCredito tarjetaCredito;
    Regalo regalo;

    public TarjetaCredito getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public Regalo getRegalo() {
        return regalo;
    }

    public void setRegalo(Regalo regalo) {
        this.regalo = regalo;
    }

    public void agregarTarjetaCredito(Cuenta cuenta, int nomina){
        cuenta.deducirSaldo(180);
        this.tarjetaCredito = new TarjetaCredito(new Double(nomina * 0.6).floatValue(), cuenta);
    }
}
