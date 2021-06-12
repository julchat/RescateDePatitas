package domain;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Control oControl = new Control();
        CargaDatos cargador = new CargaDatos();
        Scanner entrada = new Scanner(System.in);

        cargador.menu(oControl);
    }
}
