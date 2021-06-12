package domain.organizaciones;

import java.util.List;

public class Organizaciones {
    private List<Organizacion> organizaciones;

    // Getters and Setters
    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

    public void setOrganizaciones(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
    }

    public void crearOrganizacion(Organizacion organizacion) {
        this.organizaciones.add(organizacion);
    }

    public void eliminarOrganizacion(Organizacion organizacion) {
        this.organizaciones.remove(organizacion);
    }

    // Constructor
    public Organizaciones() {}

    public Organizaciones(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
    }
}
