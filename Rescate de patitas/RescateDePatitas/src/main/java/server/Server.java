package server;

import com.google.zxing.WriterException;
import database.EntityManagerHelper;
import domain.business.mascota.Chapa;
import spark.Spark;
import spark.debug.DebugScreen;

import javax.persistence.EntityManager;
import java.io.IOException;


// Unico punto de partida / unico MAIN
public class Server {
    public static void main(String[] args) throws WriterException, IOException {
        Spark.port(9000);
        Router.init();
        DebugScreen.enableDebugScreen();

        EntityManager em = EntityManagerHelper.getEntityManager();

        //Chapa chapita = new Chapa();
        //chapita.generarQR();
    }
}