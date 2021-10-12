package server;

import com.google.zxing.WriterException;
import database.EntityManagerHelper;
import domain.business.mascota.Chapa;
import domain.security.password.ValidadorPassword;
import spark.Spark;
import spark.debug.DebugScreen;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


// Unico punto de partida / unico MAIN
public class Server {
    public static void main(String[] args) throws WriterException, IOException {
        Spark.port(9000);
        Router.init();
        DebugScreen.enableDebugScreen();

        EntityManager em = EntityManagerHelper.getEntityManager();

        //Chapa chapita = new Chapa();
        //chapita.generarQR();

/*
        ValidadorPassword validador = new ValidadorPassword();
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingrese su nombre de usuario:  ");
        String usuario = entrada.nextLine();

        System.out.print("Ingrese la contraseña:  ");
        String password = entrada.nextLine();

        if(validador.esValida(usuario, password)){
            System.out.println("Es valido.");
        }
        else {
            System.out.println("Es Invalido.");
        }

        List<String> lista = validador.verificarPassword(usuario, password);
        for(String string : lista) {
            System.out.println(string);
        }*/
    }

}