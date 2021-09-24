package domain.business.publicaciones;

import domain.business.Persona;
import domain.security.Usuario;

import java.time.LocalDate;

public abstract class Publicacion {
    private EstadoPublicacion estadoPublicacion;
    private Persona autor;
    private LocalDate fecha;

    // Getters and Setters
    public EstadoPublicacion getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(EstadoPublicacion estadoPublicacion) { this.estadoPublicacion = estadoPublicacion; }

    public Persona getAutor() {
        return autor;
    }

    public void setAutor(Persona autor) {
        this.autor = autor;
    }


    // Metodos
    public void crearPublicacion(EstadoPublicacion estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
        this.fecha = LocalDate.now();
    }

    public void cambiarEstado(EstadoPublicacion nuevoEstado) {
        this.estadoPublicacion = nuevoEstado;
    }

    public boolean esVisible(Usuario usuario) {
        return this.estadoPublicacion.esVisible(usuario);
    }

    public abstract void mostrarPublicacion();

}
