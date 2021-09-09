package domain.business;

import domain.business.organizaciones.HogarDeTransito;
import domain.business.organizaciones.Organizacion;
import domain.business.organizaciones.apiHogares.APIhogares;
import domain.business.organizaciones.apiHogares.entidades.Hogar;
import domain.business.publicaciones.Publicacion;
import domain.security.password.ValidadorPassword;
import domain.security.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sistema {
    private static Sistema instancia;
    private static List<Usuario> usuarios = new ArrayList<>();
    private List<Publicacion> publicaciones = new ArrayList<>();
    private List<Organizacion> organizaciones = new ArrayList<>();
    private List<HogarDeTransito> hogaresDeTransito = new ArrayList<>();
    private List<MascotaPerdida> mascotasPerdidas = new ArrayList<>();
    private List<Mascota> mascotasEnAdopcion = new ArrayList<>();
    private ValidadorPassword validador = new ValidadorPassword();

    {
        APIhogares apIhogares = APIhogares.getInstance();
        try {
             for(int i=1; i<= apIhogares.cantidadPaginas(); i++) {
                List<Hogar> hogares = apIhogares.conjuntoHogares(i);
                for(Hogar hogar : hogares) {
                    HogarDeTransito hogarDeTransito = new HogarDeTransito();
                    hogarDeTransito.mappearHogar(hogar);
                    hogaresDeTransito.add(hogarDeTransito);
                }
             }
        } catch (IOException e) {

        }
    }

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

    public void agregarMascotaPerdida(MascotaPerdida mascotaPerdida) {
        this.mascotasPerdidas.add(mascotaPerdida);
    }

    public void mostrarMascotasEnAdopcion() {
        for(Mascota mascotaEnAdopcion : mascotasEnAdopcion) {
            mascotaEnAdopcion.mostrarDatosMascota();
        }
    }

    public void agregarMascotaEnAdopcion(Mascota mascotaAdopcion) { this.mascotasEnAdopcion.add(mascotaAdopcion); }

    public List<Usuario> getUsuarios() { return usuarios; }

    public List<Publicacion> getPublicaciones() { return publicaciones; }

    public List<Organizacion> getOrganizaciones() { return organizaciones; }

    public List<HogarDeTransito> getHogaresDeTransito() { return hogaresDeTransito; }

    public Usuario crearUsuario(String nombre, String contrasenia) {
        Usuario nuevoUsuario = new Usuario(nombre, contrasenia);
        if (!this.existeUsuario(nombre)) {
            this.usuarios.add(nuevoUsuario);
            return nuevoUsuario;
        } else {
            return null;
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

    public void reclamarMascotaEncontrada(MascotaPerdida mascota) {
        if(mascotasPerdidas.contains(mascota)) {
            mascotasPerdidas.remove(mascota);
        }
        else {
            System.out.println("La mascota en cuestión no se encuentra en la BD de Mascotas Perdidas.");
        }
    }

}
