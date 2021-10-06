package domain.business;

import java.util.ArrayList;
import java.util.List;

public class Sistema {

    private static Sistema instance = null;
    private List<User> usuarios;


    public Sistema() {
        this.usuarios = new ArrayList<>();
    }

    public Sistema getInstance() {
        if(instance == null) {
            instance = new Sistema();
        }
        return instance;
    }



    public void registrarUsuario(String usuario, String password) {
        /* Todo: una persona ingresa sus datos (dni, nombre y apellido)
            el Sistema se encarga de verificar dichos datos con los que se encuentran en la API
            + si los datos coinciden, entonces le permite crear un usuario con una contraseña
            - si los datos no coinciden, entonces no le permite al usuario crear un usuario
         */
    }

    public void iniciarSesion(String usuario, String password) {
        // Todo: verifica que los datos ingresados esten en la base de datos, si es correcto, ingresa al sistema
        // Todo: esta relacionado con LoginController
    }



}
