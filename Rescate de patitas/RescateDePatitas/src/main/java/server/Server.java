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
        EntityManager em = EntityManagerHelper.getEntityManager();
        Spark.port(getHerokuAssignedPort());
        Router.init();
        DebugScreen.enableDebugScreen();



    // Prueba para crear un codigo QR
        //Duenio duenio = new Duenio();
        //duenio.setNombre("Luciano");
        //duenio.setApellido("Apellido");

        //Mascota mascota = new Mascota();
        //mascota.setNombreMascota("Leo");
        //mascota.setApodoMascota("Leo");

        //Chapa chapa = new Chapa(duenio, mascota);

    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}