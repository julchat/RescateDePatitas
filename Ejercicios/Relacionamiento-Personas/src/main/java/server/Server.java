package server;

import javax.persistence.EntityManager;
import database.EntityManagerHelper;
import spark.Spark;
import spark.debug.DebugScreen;


// Unico punto de partida / unico MAIN
public class Server {
    public static void main(String[] args) {
        Spark.port(9000);
        Router.init();
        DebugScreen.enableDebugScreen();

        //EntityManager em = EntityManagerHelper.getEntityManager();

    }
}