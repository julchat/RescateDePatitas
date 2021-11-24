package domain.business.users;

import domain.business.ubicacion.Lugar;
import domain.business.mascota.MascotaPerdida;
import domain.business.publicaciones.PublicacionMascotaPerdida;
import domain.repositorios.RepositorioPublicaciones;
import domain.repositorios.factories.FactoryRepositorioPublicaciones;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rescatista")
@DiscriminatorValue("Rescatista")
public class Rescatista extends Persona {

    private boolean puedeAlojarMascota;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Column(name = "mascotasAlojadas")
    private List<MascotaPerdida> mascotasAlojadas = new ArrayList<>();


    // Getters and Setters
    public boolean isPuedeAlojarMascota() {
        return puedeAlojarMascota;
    }

    public void setPuedeAlojarMascota(boolean puedeAlojarMascota) {
        this.puedeAlojarMascota = puedeAlojarMascota;
    }

    public List<MascotaPerdida> getMascotasAlojadas() { return mascotasAlojadas; }

    public void setMascotasAlojadas(List<MascotaPerdida> mascotasAlojadas) { this.mascotasAlojadas = mascotasAlojadas; }

    public Rescatista() { }

    // Metodos
    public void alojarMascota(MascotaPerdida mascotaPerdida) {
        Lugar nuevoLugar = new Lugar();
        mascotaPerdida.setLugarDeTransito(nuevoLugar.mapearLugar(this.getDomicilio()));
        mascotasAlojadas.add(mascotaPerdida);
    }

    public void reportarMascotaPerdida(MascotaPerdida mascotaPerdida) {
        RepositorioPublicaciones repositorioPublicaciones = FactoryRepositorioPublicaciones.get();
        PublicacionMascotaPerdida publicacionCreada = new PublicacionMascotaPerdida();
        publicacionCreada.crearPublicacion(this, mascotaPerdida);
        repositorioPublicaciones.agregar(publicacionCreada);
    }

    public void mapearDatosDuenio(Persona persona) {
        this.setNombre(persona.getNombre());
        this.setApellido(persona.getApellido());
        this.setFechaDeNacimiento(persona.getFechaDeNacimiento());
        this.setTipoDocumento(persona.getTipoDocumento());
        this.setNumeroDocumento(persona.getNumeroDocumento());
        this.setTelefono(persona.getTelefono());
        this.setEmail(persona.getEmail());
        this.setFormasDeNotificacion(persona.getFormasDeNotificacion());
        this.setContactos(persona.getContactos());
    }

    @Override
    public void mostrarDatosNoSensibles() {
        super.mostrarDatosNoSensibles();
        System.out.println("Puede alojar mascota: " + isPuedeAlojarMascota());
        System.out.println("Domicilio: ");
        System.out.println("    - Provincia: " + getDomicilio().getProvincia());
        System.out.println("    - Localidad: " + getDomicilio().getLocalidad());
        System.out.println("    - Calle: " + getDomicilio().getCalle());
        System.out.println("    - Numeración: " + getDomicilio().getNumeracion());
    }


}
