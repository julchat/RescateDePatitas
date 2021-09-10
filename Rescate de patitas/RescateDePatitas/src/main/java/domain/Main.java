package domain;

import domain.business.*;
import domain.business.organizaciones.HogarDeTransito;
import domain.views.MenuPrueba;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static MenuPrueba menuPrueba = new MenuPrueba();

    public static void main(String[] args) throws IOException {
        //menuPrueba.menuPrincipal();

        HogarDeTransito hogarDeTransito = new HogarDeTransito();
        Ubicacion nuevaUbicacion = new Ubicacion();
        MascotaPerdida mascotaEncontrada = new MascotaPerdida();

        Scanner entrada = new Scanner(System.in);

        System.out.print("LATITUD 1: ");
        nuevaUbicacion.setLatitud(entrada.nextFloat());
        System.out.print("LONGITUD 1: ");
        nuevaUbicacion.setLongitud(entrada.nextFloat());
        System.out.print("LATITUD 2: ");
        float latitud2 = entrada.nextFloat();
        System.out.print("LONGITUD 1: ");
        float longitud2 = entrada.nextFloat();

        mascotaEncontrada.setUbicacionEncontrada(nuevaUbicacion);
        mascotaEncontrada.setCaracteristicaMascotas(new ArrayList<>());
        mascotaEncontrada.setLugarDeTransito(null);
        mascotaEncontrada.setDescripcion("Linda mascota");
        mascotaEncontrada.setTipoAnimal(TipoAnimal.PERRO);
        mascotaEncontrada.setTamanio(Tamanio.GRANDE);
        mascotaEncontrada.setCarrouselFotos(null);

        System.out.println("Distancia final: " + hogarDeTransito.distancia(nuevaUbicacion.getLatitud(), nuevaUbicacion.getLongitud(), latitud2, longitud2));

        System.out.println("INGRESE UN RADIO DE BUSQUEDA: ");
        int radio = entrada.nextInt();

        menuPrueba.buscarHogarMasCercano(radio, mascotaEncontrada);
    }
}

/*

-34,7771494     Y
-58,3677434     X

-34,7817575     Y
-58,3763972     X

 */
