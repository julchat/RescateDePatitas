package domain.security;

import domain.business.Persona;
import domain.security.password.ValidadorPassword;
import excepciones.PermisosInvalidosException;

public class Usuario {
    private String usuario;
    private String contrasenia;
    private Rol rol;
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
        if(new ValidadorPassword().esValida(contrasenia)){
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
    public Usuario(String usuario, String contrasenia){
            this.usuario = usuario;
            this.rol = new User();
            if(new ValidadorPassword().esValida(contrasenia)){
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

    public void banearUsuario(Usuario usuarioBaneado) {
        // TODO: que hace aca? Lo elimina de la base de datos por un tiempo? o bloquea su inicio de sesion por X tiempo?
    }
}