package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "rescatista")
public class RescatistaDB extends EntidadPersistente {

    @OneToOne
    @JoinColumn(name = "persona_id")
    private PersonaDB personaDB;

    private boolean puedeAlojarMascota;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "domicilio")
    private DomicilioDB domicilio;

// Getters and Setters
    public PersonaDB getPersonaDB() { return personaDB; }

    public void setPersonaDB(PersonaDB personaDB) { this.personaDB = personaDB; }

    public boolean isPuedeAlojarMascota() { return puedeAlojarMascota; }

    public void setPuedeAlojarMascota(boolean puedeAlojarMascota) { this.puedeAlojarMascota = puedeAlojarMascota; }

    public DomicilioDB getDomicilio() { return domicilio; }

    public void setDomicilio(DomicilioDB domicilio) { this.domicilio = domicilio; }
}
