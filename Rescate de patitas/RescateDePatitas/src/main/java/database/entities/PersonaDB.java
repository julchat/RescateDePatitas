package database.entities;

import domain.business.users.TipoDoc;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona")
public class PersonaDB extends EntidadPersistente {

    private String nombre;
    private String apellido;
    private LocalDate fechaDeNacimiento;

    @Enumerated(EnumType.STRING)
    private TipoDoc tipoDocumento;

    private int numeroDocumento;
    private String telefono;
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "notificaciones")
    private List<NotificacionDB> formasDeNotificacion = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactos")
    private List<ContactoDB> contactos = new ArrayList<>();

    private boolean suscripto;


// Getters and Setters
    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public LocalDate getFechaDeNacimiento() { return fechaDeNacimiento; }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }

    public TipoDoc getTipoDocumento() { return tipoDocumento; }

    public void setTipoDocumento(TipoDoc tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public int getNumeroDocumento() { return numeroDocumento; }

    public void setNumeroDocumento(int numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<NotificacionDB> getFormasDeNotificacion() { return formasDeNotificacion; }

    public void setFormasDeNotificacion(List<NotificacionDB> formasDeNotificacion) { this.formasDeNotificacion = formasDeNotificacion; }

    public List<ContactoDB> getContactos() { return contactos; }

    public void setContactos(List<ContactoDB> contactos) { this.contactos = contactos; }

    public boolean isSuscripto() { return suscripto; }

    public void setSuscripto(boolean suscripto) { this.suscripto = suscripto; }
}
