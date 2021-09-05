package domain.business;

import domain.business.publicaciones.BusquedaMascotaPerdida;
import domain.business.publicaciones.Pendiente;
import domain.business.publicaciones.Publicacion;
import domain.business.notificaciones.Notificacion;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Rescatista extends Persona{
    private boolean puedeAlojarMascota;
    private Domicilio domicilio;

    // Getters and Setters
    public boolean isPuedeAlojarMascota() {
        return puedeAlojarMascota;
    }

    public void setPuedeAlojarMascota(boolean puedeAlojarMascota) {
        this.puedeAlojarMascota = puedeAlojarMascota;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    // Constructor
    public Rescatista() { }

    public Rescatista(String nombre, String apellido, LocalDate fechaDeNacimiento, TipoDoc tipoDocumento, int numeroDocumento, String telefono, String email, List<Notificacion> formasDeNotificacion, List<Contacto> contactos, boolean puedeAlojarMascota, Domicilio domicilio) {
        super(nombre, apellido, fechaDeNacimiento, tipoDocumento, numeroDocumento, telefono, email, formasDeNotificacion, contactos);
        this.puedeAlojarMascota = puedeAlojarMascota;
        this.domicilio = domicilio;
    }


    // Metodos
    public void buscarHogarDeTransito(MascotaPerdida mascotaPerdida) {
        if(this.isPuedeAlojarMascota()) {
            //todo: aloja a la mascota en este domicilio
            mascotaPerdida.setLugarDeTransito(this.getDomicilio());
        }
        else {
            // Todo: buscar el Hogar de Transito mas cercano de la ubicación que encontro a la mascota
        }
    }

    public void crearPublicacionMascotaPerdida() {}

    public void reportarMascotaPerdida(MascotaPerdida mascotaPerdida) {
        Publicacion publicacionCreada = new Publicacion();
        publicacionCreada.setAutor(this);
        publicacionCreada.setTipoPublicacion(new BusquedaMascotaPerdida());
        publicacionCreada.setEstadoPublicacion(new Pendiente());
        //publicacionCreada.getTipoPublicacion()
    }

    public void mapearDatosDuenio(Persona persona) {
        setNombre(persona.getNombre());
        setApellido(persona.getApellido());
        setFechaDeNacimiento(persona.getFechaDeNacimiento());
        setTipoDocumento(persona.getTipoDocumento());
        setNumeroDocumento(persona.getNumeroDocumento());
        setTelefono(persona.getTelefono());
        setEmail(persona.getEmail());
        setFormasDeNotificacion(persona.getFormasDeNotificacion());
        setContactos(persona.getContactos());
    }

    @Override
    public void mostrarDatosNoSensibles() {
        super.mostrarDatosNoSensibles();
        System.out.println("Puede alojar mascota: " + isPuedeAlojarMascota());
        System.out.println("Domicilio: ");
        System.out.println("    - Provincia: " + getDomicilio().getProvincia());
        System.out.println("    - Localidad: " + getDomicilio().getLocalidad());
        System.out.println("    - Calle: " + getDomicilio().getCalle());
        System.out.println("    - Numeración: " + getDomicilio().getNumero());
    }


}
