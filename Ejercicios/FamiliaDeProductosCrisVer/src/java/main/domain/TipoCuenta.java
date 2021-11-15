package java.main.domain;

public abstract class TipoCuenta {
    float interesCuenta;
    TarjetaDebito tarjetaDebito;
    float descubiertoPermitido;

    public float getInteresCuenta() {
        return interesCuenta;
    }

    public void setInteresCuenta(float interesCuenta) {
        this.interesCuenta = interesCuenta;
    }

    public TarjetaDebito getTarjetaDebito() {
        return tarjetaDebito;
    }

    public void setTarjetaDebito(TarjetaDebito tarjetaDebito) {
        this.tarjetaDebito = tarjetaDebito;
    }

    public void setDescubiertoPermitido(float descubiertoPermitido) {
        this.descubiertoPermitido = descubiertoPermitido;
    }

    public float getDescubiertoRestante(){
        return descubiertoPermitido;
    }
}
