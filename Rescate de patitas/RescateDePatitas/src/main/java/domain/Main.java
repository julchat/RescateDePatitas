package domain;

import domain.business.*;
import domain.business.organizaciones.HogarDeTransito;
import domain.views.MenuPrueba;
import org.junit.Test;
import persistence.database.EntityManagerHelper;
import persistence.entities.HogarDeTransitoDB;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Sistema miSistema = Sistema.getInstance();

    public static void main(String[] args) throws IOException {
        //menuPrueba.menuPrincipal();

        HogarDeTransito hogarDeTransito = new HogarDeTransito();
        Ubicacion nuevaUbicacion = new Ubicacion();
        MascotaPerdida mascotaEncontrada = new MascotaPerdida();

        Scanner entrada = new Scanner(System.in);

        //System.out.print("LATITUD 1: ");
        nuevaUbicacion.setLatitud(-34.7771494);
        //System.out.print("LONGITUD 1: ");
        nuevaUbicacion.setLongitud(-58.3677434);

        mascotaEncontrada.setUbicacionEncontrada(nuevaUbicacion);
        mascotaEncontrada.setCaracteristicaMascotas(new ArrayList<>());
        mascotaEncontrada.setLugarDeTransito(null);
        mascotaEncontrada.setDescripcion("Linda mascota");
        mascotaEncontrada.setTipoAnimal(TipoAnimal.PERRO);
        mascotaEncontrada.setTamanio(Tamanio.GRANDE);
        mascotaEncontrada.setCarrouselFotos(null);

        System.out.print("INGRESE UN RADIO DE BUSQUEDA: ");
        int radio = entrada.nextInt();

        HogarDeTransito hogarMasCercano = miSistema.buscarHogarMasCercano(radio, mascotaEncontrada);

        System.out.println("Distancia final: " + hogarDeTransito.distancia(nuevaUbicacion.getLatitud(), nuevaUbicacion.getLongitud(), hogarMasCercano.getLatitud(), hogarMasCercano.getLongitud()));


        HogarDeTransitoDB unHogar = new HogarDeTransitoDB();
        unHogar.setAceptaGatos(hogarMasCercano.aceptaGatos());
        unHogar.setAceptaPerros(hogarMasCercano.aceptaPerros());
        unHogar.setCapacidad(hogarMasCercano.getCapacidad());
        unHogar.setLatitud(hogarMasCercano.getLatitud());
        unHogar.setDireccion(hogarMasCercano.getDireccion());
        unHogar.setLongitud(hogarMasCercano.getLongitud());
        unHogar.setLugaresDisponibles(hogarMasCercano.getLugaresDisponibles());
        unHogar.setNombreOrganizacion(hogarMasCercano.getNombreOrganizacion());
        unHogar.setTelefono(hogarMasCercano.getTelefono());
        unHogar.setPoseePatio(hogarMasCercano.poseePatio());
        unHogar.setCaracteristicasAdmitidas(new ArrayList<>());
        unHogar.setMascotasActuales(new ArrayList<>());
        agregarObjeto(unHogar);

    }

    public static void agregarObjeto(Object unObjeto) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();
        em.persist(unObjeto);
        EntityManagerHelper.commit();
    }
}

/*

-34,7771494     Y
-58,3677434     X

-34,7817575     Y
-58,3763972     X

 */
