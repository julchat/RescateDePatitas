package domain;

import java.util.ArrayList;
import java.util.Scanner;

public class CargaDatos {
    Scanner entrada = new Scanner(System.in);
    String controlcito;


    public void menu(Control control) {
        while(true) {
            System.out.println("Ingrese la operacion que desea realizar:");
            System.out.println("C: Cargar cliente");
            System.out.println("B: Buscar cliente");
            System.out.println("T: Terminar proceso");
            String codigo = entrada.next();

            switch(codigo) {
                case "C":
                    this.cargarDatosPorCliente(control);
                    break;
                case "B":
                    this.buscarCliente(control);
                    break;
                case "T":
                    return;
            }
        }
    }


    public void cargarDatosPorCliente(Control oControl) {

        System.out.print("Ingrese el numero de documento del cliente: ");
        int numeroDocumento = entrada.nextInt();
        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = entrada.next();

        System.out.println("¿Cuantas cuentas tendrá este cliente? Ingrese su cantidad: ");
        int cantidadCuentas = entrada.nextInt();

        ArrayList<Cuenta> cuentas = new ArrayList<>();

        for(int i=0; i<cantidadCuentas; i++){

            System.out.println("Ingrese el saldo de dicha cuenta: ");
            int saldoCuenta = entrada.nextInt();

            cuentas.add(new Cuenta(saldoCuenta));
        }
        oControl.agregarCliente(new Cliente(numeroDocumento, nombreCliente, cuentas));
    }

    public void buscarCliente(Control control) {
        System.out.print("Ingrese el documento del cliente que quiere buscar: ");
        int numeroDocumento = entrada.nextInt();
        //oControl.buscarCliente(numeroDocumento);
        if(control.noExisteCliente(numeroDocumento)) {
            System.out.println("No existe el cliente.");
        }
        else {
            System.out.print("Ingrese el saldo piso para filtrar las cuentas del cliente: ");
            int saldoPiso = entrada.nextInt();
            control.cantidadCuentasBuscadas(numeroDocumento, saldoPiso);
            }
        }
}
