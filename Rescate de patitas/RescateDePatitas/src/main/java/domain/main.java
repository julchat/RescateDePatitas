package domain;

import domain.password.ValidadorPassword;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        ValidadorPassword validador = new ValidadorPassword();
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingrese su nombre de usuario:  ");
        String usuario = entrada.nextLine();

        System.out.print("Ingrese la contraseña:  ");
        String password = entrada.nextLine();

        if(validador.esValida(password)){
            System.out.println("Es valido.");
        }
        else {
            System.out.println("Es Invalido.");
        }
    }

    
}
