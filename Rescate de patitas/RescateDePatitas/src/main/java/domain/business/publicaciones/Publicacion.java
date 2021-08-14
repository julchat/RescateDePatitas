package domain.business.publicaciones;

import domain.business.Persona;

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

    public boolean esVisible() {
        return this.estadoPublicacion.esVisible();
    }
}
