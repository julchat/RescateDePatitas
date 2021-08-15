package domain.business;

import domain.business.organizaciones.Organizacion;
import domain.business.publicaciones.Publicacion;
import domain.password.ValidadorPassword;
import domain.security.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sistema {
    private static Sistema instancia;
    private static List<Usuario> usuarios = new ArrayList<>();
    private List<Publicacion> publicaciones = new ArrayList<>();
    private List<Organizacion> organizaciones = new ArrayList<>();
    private ValidadorPassword validador = new ValidadorPassword();
    // Todo: preguntas que deben estar si o si


    public static Sistema getInstance() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }


    public void crearUsuario(String nombre, String contrasenia) {
        Usuario nuevoUsuario = new Usuario(nombre, contrasenia);
        if (!this.buscarUsuario(nuevoUsuario)) {
            this.usuarios.add(nuevoUsuario);
        } else {
            System.out.println("El usuario ya se encuentra en el Sistema.");
        }
    }

    public boolean buscarUsuario(Usuario usuarioBuscado) {
        return this.usuarios.contains(usuarioBuscado);
    }

    public boolean existeUsuario(String usuarioBuscado) {
        return this.usuarios.stream().map(usuario -> usuario.getUsuario()).collect(Collectors.toList()).contains(usuarioBuscado);
    }

    public boolean validarContrasenia(String contrasenia) {
        return validador.esValida(contrasenia);
    }
}
