package domain.business.users;

import domain.business.ubicacion.Domicilio;
import domain.business.ubicacion.Lugar;
import domain.business.mascota.MascotaPerdida;
import domain.business.publicaciones.PublicacionMascotaPerdida;

import java.util.ArrayList;
import java.util.List;

public class Rescatista extends Persona {
    private boolean puedeAlojarMascota;
    private Domicilio domicilio;
    private List<MascotaPerdida> mascotasAlojadas = new ArrayList<>();

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

    public List<MascotaPerdida> getMascotasAlojadas() { return mascotasAlojadas; }

    public void setMascotasAlojadas(List<MascotaPerdida> mascotasAlojadas) { this.mascotasAlojadas = mascotasAlojadas; }


    // Metodos
    public void alojarMascota(MascotaPerdida mascotaPerdida) {
        Lugar nuevoLugar = new Lugar();
        mascotaPerdida.setLugarDeTransito(nuevoLugar.mapearLugar(this.getDomicilio()));
        mascotasAlojadas.add(mascotaPerdida);
    }

    public void reportarMascotaPerdida(MascotaPerdida mascotaPerdida) {
        PublicacionMascotaPerdida publicacionCreada = new PublicacionMascotaPerdida();
        publicacionCreada.crearPublicacion(this, mascotaPerdida);
        // TODO: agregar la publicacion al Repositorio de Publicaciones
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
