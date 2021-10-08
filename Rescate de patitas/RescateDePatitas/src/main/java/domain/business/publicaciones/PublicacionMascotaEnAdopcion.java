package domain.business.publicaciones;

import domain.business.users.Duenio;
import domain.business.mascota.Mascota;
import domain.business.users.Persona;

import java.util.ArrayList;
import java.util.List;

public class PublicacionMascotaEnAdopcion extends Publicacion{
    private List<Respuesta> respuestasOrganizacion;
    // TODO: medio redundante ya que el Autor de la Publicacion es el Dueño de la Mascota
    private Mascota mascotaElegida;
    private List<Persona> personasInteresadas;


    // Getters and Setters
    public List<Respuesta> getRespuestasOrganizacion() { return respuestasOrganizacion; }

    public void setRespuestasOrganizacion(List<Respuesta> respuestasOrganizacion) { this.respuestasOrganizacion = respuestasOrganizacion; }

    public Mascota getMascotaElegida() { return mascotaElegida; }

    public void setMascotaElegida(Mascota mascotaElegida) { this.mascotaElegida = mascotaElegida; }


    // Métodos
    public void crearPublicacion(Persona autor, Mascota mascotaElegida, List<Respuesta> respuestasOrganizacion) {
        super.crearPublicacion(new Pendiente());
        this.setAutor(autor);
        this.setMascotaElegida(mascotaElegida);
        this.setRespuestasOrganizacion(respuestasOrganizacion);
        this.personasInteresadas = new ArrayList<>();
    }

    public void nuevoInteresado(Persona interesado) {
        this.personasInteresadas.add(interesado);
    }


    @Override
    public void mostrarPublicacion() {
        this.mascotaElegida.mostrarDatosMascota();
        this.getAutor().mostrarDatosNoSensibles();
        for(Respuesta respuesta : respuestasOrganizacion) {
            respuesta.mostrarRespuesta();
        }
        System.out.println("Personas interesadas: " + personasInteresadas.size());
    }
}
