package domain.business;

import domain.notificaciones.Notificacion;

import java.time.LocalDateTime;
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

    public Rescatista(String nombre, String apellido, LocalDateTime fechaDeNacimiento, TipoDoc tipoDocumento, int numeroDocumento, String telefono, String email, List<Notificacion> formasDeNotificacion, List<Contacto> contactos, boolean puedeAlojarMascota, Domicilio domicilio) {
        super(nombre, apellido, fechaDeNacimiento, tipoDocumento, numeroDocumento, telefono, email, formasDeNotificacion, contactos);
        this.puedeAlojarMascota = puedeAlojarMascota;
        this.domicilio = domicilio;
    }


    // Metodos
    public void buscarHogarDeTransito(MascotaPerdida mascotaPerdida) {}

    public void crearPublicacionMascotaPerdida() {}
}
