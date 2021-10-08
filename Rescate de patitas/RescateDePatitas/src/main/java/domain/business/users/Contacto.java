package domain.business.users;

import domain.business.EntidadPersistente;
import domain.business.notificaciones.Notificacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contacto")
public class Contacto extends EntidadPersistente {

    private String nombreContacto;
    private String apellidoContacto;
    private int telefonoContacto;
    private String emailContacto;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "notificaciones")
    private List<Notificacion> formasDeNotificacionContacto = new ArrayList<>();


    // Getters and Setters
    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) { this.nombreContacto = nombreContacto; }

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

    public List<Notificacion> getFormasDeNotificacionContacto() {
        return formasDeNotificacionContacto;
    }

    public void setFormasDeNotificacionContacto(List<Notificacion> formasDeNotificacionContacto) {
        this.formasDeNotificacionContacto = formasDeNotificacionContacto;
    }

    public void agregarFormaDeNotificacion(Notificacion notificacion) {
        this.formasDeNotificacionContacto.add(notificacion);
    }

    public void quitarFormaDeNotificacion(Notificacion notificacion) {
        this.formasDeNotificacionContacto.remove(notificacion);
    }


    // Constructor
    public Contacto() {}

}
