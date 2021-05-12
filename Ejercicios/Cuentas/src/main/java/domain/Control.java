package domain;

import domain.exception.CuentaNoEncontrada;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Control {
    private List<Cliente> clientes = new ArrayList<>();


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
    public Cliente buscarCliente(int numeroDocumento) {
        return clientes.stream().filter(cliente -> cliente.esMiDocumento(numeroDocumento)).findFirst().get();
    }

    public int pedirSaldo(Cliente cliente, Cuenta cuenta) {
        if (cliente.tieneCuenta(cuenta)) {
            return cuenta.getSaldo();
        }
        else {
            throw new CuentaNoEncontrada();
        }
    }

    public List<Cuenta> chequearCuentas(Cliente clienteBuscado, int saldoPiso) {
        return clienteBuscado.getCuentas().stream().filter(cuenta -> cuenta.superaMonto(saldoPiso)).collect(Collectors.toList());
    }

    public int cantidadCuentasBuscadas(Cliente clienteBuscado, int saldoPiso) {
        return this.chequearCuentas(clienteBuscado, saldoPiso).size();
    }

    public void anotarCuenta(Cuenta cuenta, Cliente cliente) {
        this.agregarCliente(cliente);
        cliente.agregarCuenta(cuenta);
    }

}

