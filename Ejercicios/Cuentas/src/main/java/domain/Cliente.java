package domain;

import java.util.List;
import java.util.Arrays;

public class Cliente {
    private int numeroDocumento;
    private String nombreCliente;
    private List<Cuenta> cuentas;

    // Getters and Setters
    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void agregarCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }

    // Constructor
    public Cliente(int numeroDocumento, String nombreCliente, List<Cuenta> cuentas) {
        this.numeroDocumento = numeroDocumento;
        this.nombreCliente = nombreCliente;
        this.cuentas = cuentas;
    }

    public Cliente() {
    }


    // Metodos
    public int cantidadCuentas() {
        return cuentas.size();
    }

    public boolean esMiDocumento(int numeroDocumento) {
        return this.numeroDocumento == numeroDocumento;
    }

    public boolean tieneCuenta(Cuenta cuentaBuscada) {
        return cuentas.stream().anyMatch(cuenta -> cuenta == cuentaBuscada);
    }
}
