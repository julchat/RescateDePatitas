package java.main.domain.creacion;

import java.main.domain.*;

public class JovenAbstract extends CuentaAbstractFactory {
    public void prepararCuenta(Cliente dueño) {
        super.prepararCuenta(dueño);
        cuentaEnProceso.setTipo(new Joven());
    }
    @Override
    public float configurarIntereses() {
        float interes = 2;
        cuentaEnProceso.getTipo().setInteresCuenta(interes);
        return interes;
    }

    @Override
    public float configurarDescubierto() {
        return 0;
    }

    @Override
    public TarjetaDebito agregarTarjetaDebito() {
        TarjetaDebito tarjeta = new TarjetaDebito(cuentaEnProceso);
        this.cuentaEnProceso.getTipo().setTarjetaDebito(tarjeta);
        return tarjeta;
    }

    @Override
    public TarjetaCredito agregarTarjetaCredito() {
        return null;
    }

    @Override
    public Regalo prepararRegalo() {
        Regalo regalo = new CDMusica();
        Joven joven = (Joven) this.cuentaEnProceso.getTipo();
        joven.setRegalo(regalo);
        return regalo;
    }
}
