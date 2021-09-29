package domain;

import domain.business.*;

import persistence.database.BDUtils;
import org.apache.log4j.BasicConfigurator;
import domain.views.MenuPrueba;

import javax.persistence.EntityManager;
import java.io.IOException;

public class Main {

    private static Sistema miSistema = Sistema.getInstance();

    private static MenuPrueba menuPrueba = new MenuPrueba();

    public static void main(String[] args) throws IOException {

        //BasicConfigurator.configure(); // configura el logger
        EntityManager em = BDUtils.getEntityManager();
        //BDUtils.comenzarTransaccion(em);

        menuPrueba.menuPrincipal();

        /*

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
        mascotaEncontrada.setCarrouselFotos(new ArrayList<>());

        System.out.print("INGRESE UN RADIO DE BUSQUEDA: ");
        int radio = entrada.nextInt();

        HogarDeTransito hogarMasCercano = miSistema.buscarHogarMasCercano(radio, mascotaEncontrada);

        System.out.println("Distancia final: " + hogarDeTransito.distancia(nuevaUbicacion.getLatitud(), nuevaUbicacion.getLongitud(), hogarMasCercano.getLatitud(), hogarMasCercano.getLongitud()));
*/
       /* HogarDeTransitoDB unHogar = new HogarDeTransitoDB();
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
        unHogar.setMascotasActuales(new ArrayList<>());*/
    }

}

/*

-34,7771494     Y
-58,3677434     X

-34,7817575     Y
-58,3763972     X

 */
