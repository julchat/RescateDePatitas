package domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Sistema {

    private static Sistema instance = null;
    private List<User> usuarios;
    private List<Autorizacion> autorizaciones;


    // Getters and Setters
    public List<User> getUsuarios() { return usuarios; }

    public void setUsuarios(List<User> usuarios) { this.usuarios = usuarios; }

    public List<Autorizacion> getAutorizaciones() { return autorizaciones; }

    public void setAutorizaciones(List<Autorizacion> autorizaciones) { this.autorizaciones = autorizaciones; }


    // Metodos
    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.autorizaciones = new ArrayList<>();
    }

    public static Sistema getInstance() {
        if(instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    public void registrarUsuario(String nombre, String apellido, int documento) {
        /* Todo: una persona ingresa sus datos (dni, nombre y apellido)
            el Sistema se encarga de verificar dichos datos con los que se encuentran en la API
            + si los datos coinciden, entonces le permite crear un usuario con una contraseña
            - si los datos no coinciden, entonces no le permite al usuario crear un usuario
         */
        Scanner entrada = new Scanner(System.in);

        User newUser = new User();
        newUser.setUsuario(entrada.nextLine());
        newUser.setPassword(entrada.nextLine());
        newUser.setActor(new Usuario());
        newUser.getActor().setNombre(nombre);
        newUser.getActor().setApellido(apellido);
        newUser.getActor().setNroDocumento(documento);
        this.getUsuarios().add(newUser);
    }

    public void iniciarSesion(String usuario, String password) {
        // Todo: verifica que los datos ingresados esten en la base de datos, si es correcto, ingresa al sistema
        // Todo: esta relacionado con LoginController
        if(this.getUsuarios().stream().anyMatch(user -> user.getUsuario().equals(usuario)) && this.getUsuarios().stream().anyMatch(user -> user.getPassword().equals(password))) {
            User userLogin = this.getUsuarios().stream().filter(user -> user.getUsuario().equals(usuario)).collect(Collectors.toList()).get(0);
            userLogin.setSesion(new Sesion());
        }
    }

    public Actor personaLoggeada(Persona persona) {
       return this.usuarios.stream().filter(user -> user.getActor().getPersona().equals(persona)).collect(Collectors.toList()).get(0).getActor();
    }


}
