package domain;

import domain.password.ValidadorPassword;
import domain.views.MenuPrueba;

import java.util.Scanner;

public class main {

    private static MenuPrueba menuPrueba = new MenuPrueba();

    public static void main(String[] args) {
        menuPrueba.iniciarMenu();
    }
}
