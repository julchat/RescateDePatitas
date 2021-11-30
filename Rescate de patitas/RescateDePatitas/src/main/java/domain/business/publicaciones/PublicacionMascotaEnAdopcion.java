package domain.business.publicaciones;

import domain.business.mascota.Mascota;
import domain.business.users.Persona;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "publicacion_mascota_en_adopcion")
@DiscriminatorColumn(name = "mascota_en_adopcion")
public class PublicacionMascotaEnAdopcion extends Publicacion {

    @OneToOne
    @JoinColumn(name = "mascotaElegida")
    private Mascota mascotaElegida;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_publicacion")
    private List<Respuesta> respuestas = new ArrayList<>();

    @Transient
    private List<Persona> personasInteresadas = new ArrayList<>();


    // Getters and Setters
    public List<Respuesta> getRespuestas() { return respuestas; }

    public void setRespuestas(List<Respuesta> respuestas) { this.respuestas = respuestas; }

    public Mascota getMascotaElegida() { return mascotaElegida; }

    public void setMascotaElegida(Mascota mascotaElegida) { this.mascotaElegida = mascotaElegida; }

    public List<Persona> getPersonasInteresadas() { return personasInteresadas; }

    public void setPersonasInteresadas(List<Persona> personasInteresadas) { this.personasInteresadas = personasInteresadas; }

    // Métodos
    public void crearPublicacion(Persona autor, Mascota mascotaElegida, List<Respuesta> respuestasOrganizacion) {
        super.crearPublicacion();
        this.setAutor(autor);
        this.setMascotaElegida(mascotaElegida);
        this.setRespuestas(respuestasOrganizacion);
        this.personasInteresadas = new ArrayList<>();
    }

    public void nuevoInteresado(Persona interesado) {
        this.personasInteresadas.add(interesado);
    }
}
