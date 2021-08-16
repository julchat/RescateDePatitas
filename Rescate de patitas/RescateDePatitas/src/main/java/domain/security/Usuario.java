package domain.security;

import domain.business.Persona;
import domain.password.ValidadorPassword;
import excepciones.PermisosInvalidosException;

public class Usuario {
    private String usuario;
    private String contrasenia;
    private Rol rol; //Para evitar andar chequeando tipo (es un admin? -> tiro excepcion) mejor delegarselo al rol y si no es admin tire excepcion
    public boolean soyAdmin = false; //Para almacenar en la DB
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
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void cambiarRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isSoyAdmin() {
        return soyAdmin;
    }

    public void setSoyAdmin(boolean soyAdmin) {
        this.soyAdmin = soyAdmin;
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
    public Usuario(String usuario, String contrasenia){
            this.usuario = usuario;
            if(new ValidadorPassword().esValida(contrasenia)){
                    this.contrasenia = contrasenia;
            } //Si no tira excepcion creo
    }

    public Usuario(){
            this.usuario = "jesucristo";
            this.contrasenia = "soyadmin";
            this.rol = new Administrador();
            soyAdmin = true;
    } //SOLO PARA PODER HACER EL PRIMER ADMINISTRADOR


    // Metodos
    public void hacerAdministrador(Usuario otroUsuario){
            if(rol.puedoCrearAdministradores()){
                    otroUsuario.soyAdmin = true;
                    otroUsuario.rol = new Administrador();
            }
            else{
                throw new PermisosInvalidosException();
            }
    }

    public void banearUsuario(Usuario usuarioBaneado) {
        // TODO: que hace aca? Lo elimina de la base de datos por un tiempo? o bloquea su inicio de sesion por X tiempo?
    }
}