package domain;

import domain.notificaciones.Notificacion;
import domain.organizaciones.Organizacion;

import java.util.Date;
import java.util.List;

public class Persona {
    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private String domicilioPersona;       // Podriamos hacer una clase Domicilio
    private String tipoDocumento;          // Podriamos hacer un enum para tipo de doc: DNI, CEDULA, PASAPORTE, etc.
    private int numeroDocumento;
    private List<Contacto> contactos;
    private List<Mascota> mascotas;
    private String domicilioDuenio;         //******
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

    public String getDomicilioPersona() {
        return domicilioPersona;
    }

    public void setDomicilioPersona(String domicilioPersona) {
        this.domicilioPersona = domicilioPersona;
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

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public void agregarMascota(Mascota mascota) {
        this.mascotas.add(mascota);
    }

    // Duda con esto
    public String getDomicilioDuenio() {
        return domicilioDuenio;
    }

    public void setDomicilioDuenio(String domicilioDuenio) {
        this.domicilioDuenio = domicilioDuenio;
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

    public Persona(String nombre, String apellido, Date fechaDeNacimiento, String domicilioPersona, String tipoDocumento,
                   int numeroDocumento, List<Contacto> contactos, List<Mascota> mascotas, String domicilioDuenio,
                   List<Notificacion> formasDeNotificacion, Organizacion organizacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.domicilioPersona = domicilioPersona;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.contactos = contactos;
        this.mascotas = mascotas;
        this.domicilioDuenio = domicilioDuenio;
        this.formasDeNotificacion = formasDeNotificacion;
        this.organizacion = organizacion;
    }


    // Metodos

    void rescatarMascota(Mascota mascota) {
        // TODO. Si la mascota tiene chapita, se le notifica al dueño
        //  en cambio, si no la tiene, se genera una publicacion
    }


    void registarMascota(Mascota mascota) {
        this.agregarMascota(mascota);
        // TODO Si se registra una mascota, deberia hacerlo "rellenando un formulario"
        //  o mejor dicho, armando la publicacion

    }

}
