package server;

import database.EntityManagerHelper;
import spark.Spark;
import spark.debug.DebugScreen;
import java.io.IOException;

// Unico punto de partida / unico MAIN
public class Server {
    public static void main(String[] args) throws IOException {
        Spark.port(9000);
        Router.init();
        DebugScreen.enableDebugScreen();

        //EntityManager em = EntityManagerHelper.getEntityManager();

    }
}