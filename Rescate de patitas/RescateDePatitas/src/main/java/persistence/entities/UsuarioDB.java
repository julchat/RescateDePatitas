package persistence.entities;

import domain.security.TipoRol;
import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class UsuarioDB extends EntidadPersistente {

    private String usuario;
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    private TipoRol rol;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id")
    private PersonaDB persona;


// Getters and Setters
    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasenia() { return contrasenia; }

    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public TipoRol getRol() { return rol; }

    public void setRol(TipoRol rol) { this.rol = rol; }

    public PersonaDB getPersona() { return persona; }

    public void setPersona(PersonaDB persona) { this.persona = persona; }
}
