package domain;

import domain.exception.CuentaNoEncontrada;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Control {
    private List<Cliente> clientes;

    public Control() {

    }

    // Getter and Setter
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    // Constructor
    public Control(List<Cliente> clientes) {
        this.clientes = clientes;
    }


    // Métodos
    private Cliente buscarCliente(int numeroDocumento) {
        return (Cliente) clientes.stream().filter(cliente -> cliente.getNumeroDocumento() == numeroDocumento);
    }

    private int pedirSaldo(Cliente cliente, Cuenta cuenta) {
        if (cliente.tieneCuenta(cuenta)) {
            return cuenta.getSaldo();
        }
        else {
            throw new CuentaNoEncontrada();
        }
    }

    public List<Cuenta> chequearCuentas(Cliente clienteBuscado, int saldoTope) {
        return (List<Cuenta>) clienteBuscado.getCuentas().stream().filter(cuenta -> cuenta.getSaldo() <= saldoTope);
    }

    public int cantidadCuentasBuscadas(Cliente clienteBuscado, int saldoTope) {
        return this.chequearCuentas(clienteBuscado, saldoTope).size();
    }

    private void anotarCuenta(Cuenta cuenta, Cliente cliente) {
        this.agregarCliente(cliente);
        cliente.agregarCuenta(cuenta);
    }

}

