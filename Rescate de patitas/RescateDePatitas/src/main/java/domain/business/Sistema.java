package domain.business;

import domain.business.organizaciones.HogarDeTransito;
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
    private List<MascotaPerdida> mascotasPerdidas = new ArrayList<>();
    private ValidadorPassword validador = new ValidadorPassword();
    // Todo: preguntas que deben estar si o si


    public static Sistema getInstance() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    public void mostrarMascotasPerdidas() {
        for (MascotaPerdida mascota : mascotasPerdidas) {
            mascota.mostrarMascota();
        }
    }


    public void crearUsuario(String nombre, String contrasenia) {
        Usuario nuevoUsuario = new Usuario(nombre, contrasenia);
        if (!this.existeUsuario(nombre)) {
            this.usuarios.add(nuevoUsuario);
        } else {
            System.out.println("El usuario ya se encuentra en el Sistema.");
        }
    }

    public Usuario buscarUsuario(String nombreUsuario) {
        return this.usuarios.stream().filter(usuario -> usuario.getUsuario().equals(nombreUsuario)).findFirst().get();
    }

    public boolean coincideContrasenia(String usuarioBuscado, String contrasenia) {
        Usuario usuario = buscarUsuario(usuarioBuscado);
        if(usuario.getContrasenia().equals(contrasenia)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean existeUsuario(String usuarioBuscado) {
        return this.usuarios.stream().map(usuario -> usuario.getUsuario()).collect(Collectors.toList()).contains(usuarioBuscado);
    }

    public boolean validarContrasenia(String contrasenia) {
        return validador.esValida(contrasenia);
    }


    public void registrarMascotaPerdida(MascotaPerdida mascotaPerdida) {
        this.mascotasPerdidas.add(mascotaPerdida);
    }

    public void reclamarMascotaEncontrada(MascotaPerdida mascota) {
        if(mascotasPerdidas.contains(mascota)) {
            mascotasPerdidas.remove(mascota);
        }
        else {
            System.out.println("La mascota en cuestión no se encuentra en la BD de Mascotas Perdidas.");
        }
    }

}
