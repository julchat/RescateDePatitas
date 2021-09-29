package persistence.entities;

import domain.business.Persona;
import domain.security.Rol;
import domain.security.TipoRol;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class UserDB extends EntidadPersistente{

    @Column(name = "user")
    private String usuario;

    @Column(name = "password")
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    private TipoRol rol;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona")
    private PersonaDB persona;


// Getters and Setters

}
