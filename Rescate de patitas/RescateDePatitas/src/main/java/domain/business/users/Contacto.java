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
    private String telefonoContacto;
    private String emailContacto;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "formasDeNotificacion")
    private List<Notificacion> formasDeNotificacion = new ArrayList<>();


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

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public List<Notificacion> getFormasDeNotificacion() {
        return formasDeNotificacion;
    }

    public void setFormasDeNotificacion(List<Notificacion> formasDeNotificacionContacto) {
        this.formasDeNotificacion = formasDeNotificacionContacto;
    }

    public void agregarFormaDeNotificacion(Notificacion notificacion) {
        this.formasDeNotificacion.add(notificacion);
    }

    public void quitarFormaDeNotificacion(Notificacion notificacion) {
        this.formasDeNotificacion.remove(notificacion);
    }


    // Constructor
    public Contacto() {}

}
