package domain.security;

import domain.business.EntidadPersistente;
import domain.business.users.Persona;
import domain.security.password.AESEncryptionDecryption;
import domain.security.password.ValidadorPassword;
import excepciones.PermisosInvalidosException;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadPersistente {

    private String nombreUsuario;
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    private TipoRol tipoRol;

    @Transient
    private Rol rol;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id")
    private Persona persona;


    // Getters and Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String usuario) {
        this.nombreUsuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        if(new ValidadorPassword().esValida(nombreUsuario, contrasenia)){
            this.contrasenia = contrasenia;
        }
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
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


    // Metodos
    public void hacerAdministrador(Usuario otroUsuario){
            if(rol.puedoCrearAdministradores()){
                    otroUsuario.rol = new Admin();
            }
            else{
                throw new PermisosInvalidosException();
            }
    }

    public boolean validarLogin(String usuario, String password) {
        if (StringUtils.isNotEmpty(usuario) && StringUtils.isNotEmpty(password)) {
            String passwordDesencriptado = AESEncryptionDecryption.decrypt(this.contrasenia);
            return usuario.equals(this.nombreUsuario) && password.equals(passwordDesencriptado);
        }
        else {
            return false;
        }
    }

}