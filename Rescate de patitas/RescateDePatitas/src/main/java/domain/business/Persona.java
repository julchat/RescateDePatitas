package domain.business;

import domain.notificaciones.Notificacion;
import domain.organizaciones.Organizacion;

import java.util.Date;
import java.util.List;

public class Persona {
    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private Domicilio domicilio;       // Podriamos hacer una clase Domicilio
    private String tipoDocumento;          // Podriamos hacer un enum para tipo de doc: DNI, CEDULA, PASAPORTE, etc.
    private int numeroDocumento;
    private List<Contacto> contactos;
    private List<Notificacion> formasDeNotificacion;
    private Organizacion organizacion;

    // Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public void agregarContacto(Contacto contacto) {
        this.contactos.add(contacto);
    }

    public List<Notificacion> getFormasDeNotificacion() {
        return formasDeNotificacion;
    }

    public void setFormasDeNotificacion(List<Notificacion> formasDeNotificacion) {
        this.formasDeNotificacion = formasDeNotificacion;
    }

    public void agregarNuevaNotificacion(Notificacion formaDeNotificacion) {
        this.formasDeNotificacion.add(formaDeNotificacion);
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }


    // Constructor
    public Persona() {}

    public Persona(String nombre, String apellido, Date fechaDeNacimiento, Domicilio domicilio, String tipoDocumento,
                   int numeroDocumento, List<Contacto> contactos, List<Mascota> mascotas, String domicilioDuenio,
                   List<Notificacion> formasDeNotificacion, Organizacion organizacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.domicilio = domicilio;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.contactos = contactos;
        this.formasDeNotificacion = formasDeNotificacion;
        this.organizacion = organizacion;
    }


    // Metodos
    public void crearPublicacion() {

    }
}
