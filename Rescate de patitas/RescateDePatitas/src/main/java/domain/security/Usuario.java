package domain.security;

import domain.business.EntidadPersistente;
import domain.business.users.Persona;
import domain.security.password.ValidadorPassword;
import excepciones.PermisosInvalidosException;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadPersistente {

    private String usuario;
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    private TipoRol tipoRol;

    @Transient
    private Rol rol;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id")
    private Persona persona;


    // Getters and Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        if(new ValidadorPassword().esValida(usuario, contrasenia)){
            this.contrasenia = contrasenia;
        }
    }

    public Rol getRol() {
        return rol;
    }

    public void cambiarRol(Rol rol) {
        this.rol = rol;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    // Constructor
    public Usuario() {}

    public Usuario(String usuario, String contrasenia){
            this.usuario = usuario;
            this.rol = new User();
            if(new ValidadorPassword().esValida(usuario, contrasenia)){
                    this.contrasenia = contrasenia;
            } //Si no tira excepcion creo
    }


    // Metodos
    // Solamente si uno es Admin
    public void hacerAdministrador(Usuario otroUsuario){
            if(rol.puedoCrearAdministradores()){
                    otroUsuario.rol = new Admin();
            }
            else{
                throw new PermisosInvalidosException();
            }
    }

}