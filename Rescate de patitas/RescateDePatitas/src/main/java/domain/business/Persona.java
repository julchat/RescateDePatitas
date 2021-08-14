package domain.business;

import domain.notificaciones.Notificacion;

import java.util.Date;
import java.util.List;

public class Persona {
    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private TipoDoc tipoDocumento;          // Podriamos hacer un enum para tipo de doc: DNI, CEDULA, PASAPORTE, etc.
    private int numeroDocumento;
    private int telefono;
    private String email;
    private List<Notificacion> formasDeNotificacion;
    private List<Contacto> contactos;
    private boolean suscripto;

    // Getters and Setters
    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public Date getFechaDeNacimiento() { return fechaDeNacimiento; }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }

    public TipoDoc getTipoDocumento() { return tipoDocumento; }

    public void setTipoDocumento(TipoDoc tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public int getNumeroDocumento() { return numeroDocumento; }

    public void setNumeroDocumento(int numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public int getTelefono() { return telefono; }

    public void setTelefono(int telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<Notificacion> getFormasDeNotificacion() { return formasDeNotificacion; }

    public void setFormasDeNotificacion(List<Notificacion> formasDeNotificacion) { this.formasDeNotificacion = formasDeNotificacion; }

    public void agregarNuevaFormaDeNotificacion(Notificacion nuevaFormaDeNotificacion) { this.formasDeNotificacion.add(nuevaFormaDeNotificacion); }

   public void quitarFormaDeNotificacion(Notificacion formaDeNotificacion) {
        if(this.formasDeNotificacion.contains(formaDeNotificacion)) {
            this.formasDeNotificacion.remove(formaDeNotificacion);
        }
        else {
            System.out.println("Dicha forma de Notificación no se encuentra en la lista de notificaciones de la Persona.");
        }
   }

    public List<Contacto> getContactos() { return contactos; }

    public void setContactos(List<Contacto> contactos) { this.contactos = contactos; }

    public void agregarContacto(Contacto nuevoContacto) { this.contactos.add(nuevoContacto);}

    public void quitarContacto(Contacto contacto) {
        if(this.contactos.contains(contacto)) {
            this.contactos.remove(contacto);
        }
        else {
            System.out.println("El contacto no se encuentra en la lista de contactos de la Persona");
        }
    }

    // Constructor
    public Persona() {}

    public Persona(String nombre, String apellido, Date fechaDeNacimiento, TipoDoc tipoDocumento, int numeroDocumento, int telefono, String email, List<Notificacion> formasDeNotificacion, List<Contacto> contactos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.email = email;
        this.formasDeNotificacion = formasDeNotificacion;
        this.contactos = contactos;
    }

    // Metodos
    public void crearPublicacionParaAdoptar() {
    }

    public void suscribirseNovedades() {
        this.suscripto = true;
    }

    public void desuscribirseNovedades() {
        this.suscripto = false;
    }
}
