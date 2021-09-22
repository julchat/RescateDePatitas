package persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
public class AdminDB extends PersonaDB{

    @ManyToOne
    @Column(name = "Organizacion")
    private OrganizacionDB organizacion;

// Getters and Setters
    public OrganizacionDB getOrganizacion() { return organizacion; }

    public void setOrganizacion(OrganizacionDB organizacion) { this.organizacion = organizacion; }
}
