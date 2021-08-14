package domain;

import domain.cuentas.CajaDeAhorro;
import domain.cuentas.CuentaCorriente;
import domain.cuentas.TarjetaDeCredito;

import java.util.List;

public class Banco {
    private List<CuentaCorriente> cuentasCorrientes;
    private List<TarjetaDeCredito> tarjetasDeCredito;
    private List<CajaDeAhorro> cajasDeAhorro;
}
