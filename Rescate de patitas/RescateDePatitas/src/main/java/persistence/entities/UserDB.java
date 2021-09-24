package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class UserDB extends EntidadPersistente{

    @Column(name = "Nombre usuario")
    private String usuario;

    @Column(name = "Contraseña")
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "Rol")
    private RolDB rol;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Persona")
    private PersonaDB persona;

// Getters and Setters
    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasenia() { return contrasenia; }

    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public RolDB getRol() { return rol; }

    public void setRol(RolDB rol) { this.rol = rol; }

    public PersonaDB getPersona() { return persona; }

    public void setPersona(PersonaDB persona) { this.persona = persona; }
}
