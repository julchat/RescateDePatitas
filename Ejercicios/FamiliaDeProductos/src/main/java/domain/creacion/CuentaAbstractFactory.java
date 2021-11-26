package domain.creacion;

import domain.*;

public abstract class CuentaAbstractFactory {
    Cuenta cuentaEnProceso = new Cuenta();

    public void prepararCuenta(Cliente dueño){
        cuentaEnProceso.setDueño(dueño);
    }
    public abstract float configurarIntereses();
    public abstract float configurarDescubierto();
    public abstract TarjetaDebito agregarTarjetaDebito();
    public abstract TarjetaCredito agregarTarjetaCredito();
    public abstract Regalo prepararRegalo();
    public Cuenta armarCuenta(){
        return cuentaEnProceso;
    }

}
