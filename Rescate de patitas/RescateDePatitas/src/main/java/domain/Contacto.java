package domain;

import domain.notificaciones.Notificacion;

import java.util.List;

public class Contacto {
    private String nombreContacto;
    private String apellidoContacto;
    private int telefonoContacto;
    private String emailContacto;
    private List<Notificacion> formasDeNotificacionContacto;


    // Getters and Setters
    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getApellidoContacto() {
        return apellidoContacto;
    }

    public void setApellidoContacto(String apellidoContacto) {
        this.apellidoContacto = apellidoContacto;
    }

    public int getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(int telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public void agregarFormaDeNotificacion(Notificacion notificacion) {
        this.formasDeNotificacionContacto.add(notificacion);
    }

    public void quitarFormaDeNotificacion(Notificacion notificacion) {
        this.formasDeNotificacionContacto.remove(notificacion);
    }


    // Constructor
    public Contacto() {}

    public Contacto(String nombreContacto, String apellidoContacto, int telefonoContacto, String emailContacto) {
        this.nombreContacto = nombreContacto;
        this.apellidoContacto = apellidoContacto;
        this.telefonoContacto = telefonoContacto;
        this.emailContacto = emailContacto;
    }
}
