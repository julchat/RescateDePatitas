package domain;

import domain.views.MenuPrueba;

import java.io.IOException;

public class Main {

    private static MenuPrueba menuPrueba = new MenuPrueba();

    public static void main(String[] args) throws IOException {
        menuPrueba.iniciarMenu();
    }
}
