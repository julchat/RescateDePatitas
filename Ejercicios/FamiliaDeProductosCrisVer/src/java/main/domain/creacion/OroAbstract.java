package java.main.domain.creacion;

import java.main.domain.*;

public class OroAbstract extends CuentaAbstractFactory {

    public void prepararCuenta(Cliente dueño) {
        super.prepararCuenta(dueño);
        cuentaEnProceso.setTipo(new Oro());
    }

    @Override
    public float configurarIntereses() {
        float interes = (float) 1.5;
        this.cuentaEnProceso.getTipo().setInteresCuenta(interes);
        return interes;
    }

    @Override
    public float configurarDescubierto() {
        return 0;
    }

    @Override
    public TarjetaDebito agregarTarjetaDebito() {
        TarjetaDebito tarjeta = new TarjetaDebito(cuentaEnProceso);
        cuentaEnProceso.getTipo().setTarjetaDebito(tarjeta);
        return tarjeta;
    }

    @Override
    public TarjetaCredito agregarTarjetaCredito() {
        TarjetaCredito tarjeta = new TarjetaCredito((float) (cuentaEnProceso.getDueño().getPension() * 0.6), cuentaEnProceso);
        Oro oro = (Oro) this.cuentaEnProceso.getTipo();
        oro.setTarjetaCredito(tarjeta);
        return tarjeta;
    }

    @Override
    public Regalo prepararRegalo() {
        Regalo seguro = new Seguro();
        Oro oro = (Oro) this.cuentaEnProceso.getTipo();
        oro.setRegalo(seguro);
        return seguro;
    }
}
