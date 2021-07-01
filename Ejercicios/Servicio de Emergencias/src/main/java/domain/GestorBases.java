package domain;

import java.util.Scanner;

public class GestorBases {
    Scanner entrada = new Scanner(System.in);
    Control control = new Control();

    public void menuInicio(Control control) {
        while(true) {
            System.out.println("Ingrese la base que desea ingresar:");
            System.out.println("A: Base Simple");
            System.out.println("B: Base Compuesta");
            System.out.println("T: Terminar proceso");
            String operacion = entrada.next();

            switch(operacion) {
                case "A":
                    control.agregarBase(this.cargarBaseSimple());
                    break;
                case "B":
                    control.agregarBase(this.cargarBaseCompuesta());
                    break;
                case "T":
                    return;
            }
        }
    }

    public BaseSimple cargarBaseSimple() {
        BaseSimple nuevaBase = new BaseSimple();

        System.out.print("Ingrese el nombre de la base: ");
        nuevaBase.setNombreBase(entrada.next());
        System.out.print("Ingrese la cantidad de ambulancias de la base: ");
        nuevaBase.setCantidadAmbulancias(entrada.nextInt());
        System.out.print("Ingrese el tiempo medio de la base: ");
        nuevaBase.setTiempoMedio(entrada.nextFloat());

        return nuevaBase;
    }


    public BaseCompuesta cargarBaseCompuesta() {
        BaseCompuesta nuevaBase = new BaseCompuesta();
        while(true) {
            System.out.println("Ingrese la operacion que desea realizar:");
            System.out.println("A: Agregar una Base.");
            System.out.println("T: Terminar proceso");
            String operacion = entrada.next();

            switch(operacion) {
                case "A":
                    nuevaBase.agregarBase(this.cargarBaseSimple());
                    break;
                case "T":
                    break;
            }
            return nuevaBase;
        }
    }


}
