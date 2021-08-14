package domain;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Control controlcito = new Control();
        GestorBases gestor = new GestorBases();

        Scanner entrada = new Scanner(System.in);

        gestor.menuInicio(controlcito);


    }
}
