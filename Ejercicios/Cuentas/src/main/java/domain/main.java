package domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // Creo el control de cuentas
        Control oControl = new Control();

        // Creo las cuentas
        Cuenta cuenta1 = new Cuenta(200);
        Cuenta cuenta2 = new Cuenta(500);
        Cuenta cuenta3 = new Cuenta(800);
        Cuenta cuenta4 = new Cuenta(350);

        ArrayList<Cuenta> cuentasCliente1 = new ArrayList<>();
        cuentasCliente1.add(cuenta1);
        cuentasCliente1.add(cuenta2);
        ArrayList<Cuenta> cuentasCliente2 = new ArrayList<>();
        cuentasCliente2.add(cuenta3);
        cuentasCliente2.add(cuenta4);

        // Creo el cliente
        Cliente cliente1 = new Cliente(123456789, "Pepito", cuentasCliente1);
        Cliente cliente2 = new Cliente(123123123, "Jorge", cuentasCliente2);

        oControl.agregarCliente(cliente1);
        oControl.agregarCliente(cliente2);


        // Metodos de Control
        System.out.print("Ingrese el numero de documento del cliente que quiere chequear sus cuentas: ");
        int numeroDocumento = entrada.nextInt();
        Cliente clienteBuscado = oControl.buscarCliente(numeroDocumento);

        System.out.print("Ingrese el saldo piso para buscar las cuentas: ");
        int saldoPiso = entrada.nextInt();

        List<Cuenta> cuentasBuscadas = new ArrayList<>();
        cuentasBuscadas = oControl.chequearCuentas(clienteBuscado, saldoPiso);
        int cantidadCuentasBuscadas = oControl.cantidadCuentasBuscadas(clienteBuscado, saldoPiso);

        System.out.println("La cantidad de cuentas que superan los $300 son " + cantidadCuentasBuscadas);


    }
}
