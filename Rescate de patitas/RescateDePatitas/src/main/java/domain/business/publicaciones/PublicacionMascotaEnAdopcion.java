package domain.business.publicaciones;

import domain.business.users.Duenio;
import domain.business.mascota.Mascota;
import domain.business.users.Persona;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "publicacion_mascota_en_adopcion")
@DiscriminatorColumn(name = "publicacion_mascota_en_adopcion")
public class PublicacionMascotaEnAdopcion extends Publicacion {

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "duenio_id")
    private Duenio duenioActual;

    @OneToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascotaElegida;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "respuestas")
    private List<Respuesta> respuestas = new ArrayList<>();

    @Transient
    private List<Persona> personasInteresadas = new ArrayList<>();


    // Getters and Setters
    public List<Respuesta> getRespuestas() { return respuestas; }

    public void setRespuestas(List<Respuesta> respuestas) { this.respuestas = respuestas; }

    public Mascota getMascotaElegida() { return mascotaElegida; }

    public void setMascotaElegida(Mascota mascotaElegida) { this.mascotaElegida = mascotaElegida; }


    // Métodos
    public void crearPublicacion(Persona autor, Mascota mascotaElegida, List<Respuesta> respuestasOrganizacion) {
        super.crearPublicacion(new Pendiente());
        this.setAutor(autor);
        this.setMascotaElegida(mascotaElegida);
        this.setRespuestas(respuestasOrganizacion);
        this.personasInteresadas = new ArrayList<>();
    }

    public void nuevoInteresado(Persona interesado) {
        this.personasInteresadas.add(interesado);
    }


    @Override
    public void mostrarPublicacion() {
        this.mascotaElegida.mostrarDatosMascota();
        this.getAutor().mostrarDatosNoSensibles();
        for(Respuesta respuesta : respuestas) {
            respuesta.mostrarRespuesta();
        }
        System.out.println("Personas interesadas: " + personasInteresadas.size());
    }
}
