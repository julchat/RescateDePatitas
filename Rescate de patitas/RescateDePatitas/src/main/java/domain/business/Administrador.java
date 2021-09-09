package domain.business;

import domain.business.organizaciones.Organizacion;

public class Administrador extends Persona{
    private Organizacion organizacion;

    // Getters and Setters
    public Organizacion getOrganizacion() { return organizacion; }

    public void setOrganizacion(Organizacion organizacion) { this.organizacion = organizacion; }

    // Constructor
    public Administrador() { }
}
