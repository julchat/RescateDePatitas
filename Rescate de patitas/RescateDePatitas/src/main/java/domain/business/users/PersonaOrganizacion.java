package domain.business.users;

import domain.business.organizaciones.Organizacion;

import javax.persistence.*;

@Entity
@Table(name = "persona_organizacion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Persona_Organizacion")
@DiscriminatorColumn(name = "tipo")
public class PersonaOrganizacion extends Persona {

    @OneToOne
    @JoinColumn(name = "organizacion")
    private Organizacion organizacion;


    // Getters and Setters
    public Organizacion getOrganizacion() { return organizacion; }

    public void setOrganizacion(Organizacion organizacion) { this.organizacion = organizacion; }

    public PersonaOrganizacion() {}
}