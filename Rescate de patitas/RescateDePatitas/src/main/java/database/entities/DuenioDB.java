package database.entities;

import javax.persistence.*;

@Entity
@Table(name = "duenio")
public class DuenioDB extends EntidadPersistente {

    @OneToOne
    @JoinColumn(name = "persona_id")
    private PersonaDB personaDB;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio")
    private DomicilioDB domicilio;


// Getters and Setters
    public DomicilioDB getDomicilio() { return domicilio; }

    public void setDomicilio(DomicilioDB domicilio) { this.domicilio = domicilio; }
}