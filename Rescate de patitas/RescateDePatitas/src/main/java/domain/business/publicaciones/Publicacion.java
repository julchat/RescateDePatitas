package domain.business.publicaciones;

import domain.business.Persona;
import domain.security.Usuario;

public class Publicacion {
    private TipoPublicacion tipoPublicacion;
    private EstadoPublicacion estadoPublicacion;
    private Persona Autor;

    // Getters and Setters
    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public EstadoPublicacion getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(EstadoPublicacion estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public Persona getAutor() {
        return Autor;
    }

    public void setAutor(Persona autor) {
        Autor = autor;
    }


    public void crearPublicacion(TipoPublicacion tipoPublicacion, EstadoPublicacion estadoPublicacion, Persona autor) {
        this.tipoPublicacion = tipoPublicacion;
        this.estadoPublicacion = estadoPublicacion;
        Autor = autor;
    }

    public void cambiarEstado(EstadoPublicacion nuevoEstado) {
        this.estadoPublicacion = nuevoEstado;
    }

    public boolean esVisible(Usuario usuario) {
        return this.estadoPublicacion.esVisible(usuario);
    }

    public void mostrarPublicacion() {
        System.out.println("Tipo publicación: " + getTipoPublicacion());
        System.out.println("Autor: " + getAutor().getNombre() + " " + getAutor().getApellido());
        // Tal vez algo redundante tener el autor y despues mostrar los datos del rescatista o dueño
        this.tipoPublicacion.mostrarDatos();
    }
}
