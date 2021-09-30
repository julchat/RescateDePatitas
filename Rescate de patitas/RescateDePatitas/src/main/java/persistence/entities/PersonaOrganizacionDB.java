package persistence.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "persona_organizacion")
public class PersonaOrganizacionDB extends EntidadPersistente {

    @OneToOne
    @JoinColumn(name = "persona_id")
    private PersonaDB personaDB;

    @OneToOne
    @JoinColumn(name = "organizacion_id")
    private OrganizacionDB organizacion;


// Getters and Setters
    public PersonaDB getPersonaDB() { return personaDB; }

    public void setPersonaDB(PersonaDB personaDB) { this.personaDB = personaDB; }

    public OrganizacionDB getOrganizacion() { return organizacion; }

    public void setOrganizacion(OrganizacionDB organizacion) { this.organizacion = organizacion; }
}
