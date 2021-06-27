package domain.business;

import domain.notificaciones.Notificacion;

import java.util.Date;
import java.util.List;

public class Duenio extends Persona {
    private Domicilio domicilio;
    private List<Mascota> mascotas;

    // Getters and Setters
    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    // Constructor
    public Duenio() {}

    public Duenio(String nombre, String apellido, Date fechaDeNacimiento, String tipoDocumento, int numeroDocumento, int telefono, String email, List<Notificacion> formasDeNotificacion, List<Contacto> contactos, Domicilio domicilio, List<Mascota> mascotas) {
        super(nombre, apellido, fechaDeNacimiento, tipoDocumento, numeroDocumento, telefono, email, formasDeNotificacion, contactos);
        this.domicilio = domicilio;
        this.mascotas = mascotas;
    }

    // Metodos
    public void registrarMascota(Mascota mascota) {
        Mascota mascotaARegistrar = new Mascota();
        // Crear el formulario para ingresar los datos de la mascota

        mascotas.add(mascotaARegistrar);
    }

    public void mascotaEncontrada(Mascota mascotaEncontrada) {
        if(mascotas.contains(mascotaEncontrada)) {
            mascotaEncontrada.serEncontrada();
        }
        else {
            System.out.println("No es la mascota que esta buscando este dueño.");
        }
    }

    public void iniciarSesion(String usuario, String password) {

    }
}
