package domain.creacion;

import domain.*;

public class EstandarAbstract extends CuentaAbstractFactory {
    @Override
    public void prepararCuenta(Cliente dueño) {
        super.prepararCuenta(dueño);
        cuentaEnProceso.setTipo(new Estandar());
    }

    @Override
    public float configurarIntereses() {
        float interes = (float) 0.5;
        cuentaEnProceso.setTipo(new Estandar());
        cuentaEnProceso.getTipo().setInteresCuenta(interes);
        return interes;
    }

    @Override
    public float configurarDescubierto() {
        return 0;
    }

    @Override
    public TarjetaDebito agregarTarjetaDebito() {
        return null;
    }

    @Override
    public TarjetaCredito agregarTarjetaCredito() {
        return null;
    }

    @Override
    public Regalo prepararRegalo() {
        return null;
    }
}
