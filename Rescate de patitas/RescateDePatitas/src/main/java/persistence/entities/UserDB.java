package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class UserDB extends EntidadPersistente{

    @Column(name = "Nombre Usuario")
    private String usuario;

    @Column(name = "Contraseña")
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "Rol")
    private RolDB rol;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "Persona ID")
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
