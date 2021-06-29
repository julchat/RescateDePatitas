package domain.security;

import domain.password.ValidadorPassword;
import excepciones.PermisosInvalidosException;

public class Usuario {
                private String usuario;
                private String contrasenia;
                private Rol rol; //Para evitar andar chequeando tipo (es un admin? -> tiro excepcion) mejor delegarselo al rol y si no es admin tire excepcion
                public boolean soyAdmin = false; //Para almacenar en la DB

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

                public void hacerAdministrador(Usuario otroUsuario){
                        if(rol.puedoCrearAdministradores()){
                                otroUsuario.soyAdmin = true;
                                otroUsuario.rol = new Administrador();
                        }
                        else{
                                throw new PermisosInvalidosException();
                        }
                }
}