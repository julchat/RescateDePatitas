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
    private Cliente buscarCliente(int numeroDocumento) {
        return clientes.stream().filter(cliente -> cliente.esMiDocumento(numeroDocumento)).findFirst().get();
    }

    public boolean noExisteCliente(int numeroDocumento) {
        return clientes.stream().filter(cliente -> cliente.esMiDocumento(numeroDocumento)).collect(Collectors.toList()).isEmpty();
    }

    /*
    private int pedirSaldo(Cliente cliente, Cuenta cuenta) {
        if (cliente.tieneCuenta(cuenta)) {
            return cuenta.getSaldo();
        }
        else {
            throw new CuentaNoEncontrada();
        }
    }*/



    private List<Cuenta> chequearCuentas(int numeroDocumento, int saldoPiso) {
        if(this.noExisteCliente(numeroDocumento)){
            return null;
        }
        else {
            Cliente clienteBuscado = this.buscarCliente(numeroDocumento);
            return clienteBuscado.getCuentas().stream().filter(cuenta -> cuenta.superaMonto(saldoPiso)).collect(Collectors.toList());
        }
    }

    public void cantidadCuentasBuscadas(int numeroDocumento, int saldoPiso) {
        List<Cuenta> cuentas = chequearCuentas(numeroDocumento, saldoPiso);

        if(cuentas == null) {
            System.out.println("Las cuentas que posee el cliente no superan los $" + saldoPiso);
        }
        else {
            System.out.println("La cantidad de cuentas que superan los $" + saldoPiso + " son " + cuentas.size());
        }
    }

    public void anotarCuenta(Cuenta cuenta, Cliente cliente) {
        this.agregarCliente(cliente);
        cliente.agregarCuenta(cuenta);
    }

}

