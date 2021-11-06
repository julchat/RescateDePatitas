package server;

import database.EntityManagerHelper;
import domain.business.mascota.Chapa;
import domain.business.mascota.Mascota;
import domain.business.users.Duenio;
import spark.Spark;
import spark.debug.DebugScreen;

import javax.persistence.EntityManager;
import java.io.IOException;

// Unico punto de partida / unico MAIN
public class Server {
    public static void main(String[] args) throws IOException {
        Spark.port(9000);
        Router.init();
        DebugScreen.enableDebugScreen();

        //EntityManager em = EntityManagerHelper.getEntityManager();

    // Prueba para crear un codigo QR
        //Duenio duenio = new Duenio();
        //duenio.setNombre("Luciano");
        //duenio.setApellido("Apellido");

        //Mascota mascota = new Mascota();
        //mascota.setNombreMascota("Leo");
        //mascota.setApodoMascota("Leo");

        //Chapa chapa = new Chapa(duenio, mascota);

    }
}