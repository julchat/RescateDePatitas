package domain.business.users;

import domain.business.organizaciones.Organizacion;
import domain.business.publicaciones.EstadoPublicacion;
import domain.business.publicaciones.Publicacion;
import domain.business.publicaciones.PublicacionMascotaPerdida;

public class Voluntario extends Persona {
    private Organizacion organizacion;

    // Getters and Setters
    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }


    // Metodos
    public Voluntario() {}

    public void verificarPublicacion(PublicacionMascotaPerdida publicacion, EstadoPublicacion estadoPublicacion) {
        publicacion.mostrarPublicacion();
        publicacion.cambiarEstado(estadoPublicacion);
    }

}
