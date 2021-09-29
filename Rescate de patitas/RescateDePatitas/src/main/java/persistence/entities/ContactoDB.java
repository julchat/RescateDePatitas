package persistence.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contacto")
public class ContactoDB extends EntidadPersistente{

    private String nombreContacto;
    private String apellidoContacto;
    private int telefonoContacto;
    private String emailContacto;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "notificaciones")
    private List<NotificacionDB> formasDeNotificacionContacto;


// Getters and Setters
    public String getNombreContacto() { return nombreContacto; }

    public void setNombreContacto(String nombreContacto) { this.nombreContacto = nombreContacto; }

    public String getApellidoContacto() { return apellidoContacto; }

    public void setApellidoContacto(String apellidoContacto) { this.apellidoContacto = apellidoContacto; }

    public int getTelefonoContacto() { return telefonoContacto; }

    public void setTelefonoContacto(int telefonoContacto) { this.telefonoContacto = telefonoContacto; }

    public String getEmailContacto() { return emailContacto; }

    public void setEmailContacto(String emailContacto) { this.emailContacto = emailContacto; }

    public List<NotificacionDB> getFormasDeNotificacionContacto() { return formasDeNotificacionContacto; }

    public void setFormasDeNotificacionContacto(List<NotificacionDB> formasDeNotificacionContacto) { this.formasDeNotificacionContacto = formasDeNotificacionContacto; }
}