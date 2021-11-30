package domain.creacion;

import domain.*;

public class DiezAbstract extends CuentaAbstractFactory {
    @Override
    public void prepararCuenta(Cliente dueño) {
        super.prepararCuenta(dueño);
        cuentaEnProceso.setTipo(new Diez());
    }

    @Override
    public float configurarIntereses() {
        float interes = (float) 1;
        cuentaEnProceso.getTipo().setInteresCuenta(interes);
        return interes;
    }

    @Override
    public float configurarDescubierto() {
        float descubierto = (float) (this.cuentaEnProceso.getDueño().getNomina() * 0.5);
        cuentaEnProceso.getTipo().setDescubiertoPermitido(descubierto);
        return descubierto;
    }

    @Override
    public TarjetaDebito agregarTarjetaDebito() {
        TarjetaDebito tarjeta = new TarjetaDebito(cuentaEnProceso);
        cuentaEnProceso.getTipo().setTarjetaDebito(tarjeta);
        return tarjeta;
    }

    @Override
    public TarjetaCredito agregarTarjetaCredito() {
        return null;
    }

    @Override
    public Regalo prepararRegalo() {
        Regalo regalo = new ReproductorCD();
        Diez diez = (Diez) (cuentaEnProceso.getTipo());
        diez.setRegalo(regalo);
        return regalo;
    }
}
