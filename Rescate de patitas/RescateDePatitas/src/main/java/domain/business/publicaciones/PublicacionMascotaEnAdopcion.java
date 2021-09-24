package domain.business.publicaciones;

import domain.business.Duenio;
import domain.business.Mascota;
import domain.business.Persona;
import domain.business.Respuesta;

import java.util.ArrayList;
import java.util.List;

public class PublicacionMascotaEnAdopcion extends Publicacion{
    private List<Respuesta> respuestasOrganizacion;
    private Duenio duenioActual;
    private Mascota mascotaElegida;
    private List<Persona> personasInteresadas;


    // Getters and Setters
    public List<Respuesta> getRespuestasOrganizacion() { return respuestasOrganizacion; }

    public void setRespuestasOrganizacion(List<Respuesta> respuestasOrganizacion) { this.respuestasOrganizacion = respuestasOrganizacion; }

    public Duenio getDuenioActual() { return duenioActual; }

    public void setDuenioActual(Duenio duenioActual) { this.duenioActual = duenioActual; }

    public Mascota getMascotaElegida() { return mascotaElegida; }

    public void setMascotaElegida(Mascota mascotaElegida) { this.mascotaElegida = mascotaElegida; }

    public void nuevoInteresado(Persona interesado) {
        this.personasInteresadas.add(interesado);
    }


    // Métodos
    public void crearPublicacion(EstadoPublicacion estadoPublicacion, Persona autor, Mascota mascotaElegida, List<Respuesta> respuestasOrganizacion) {
        super.crearPublicacion(estadoPublicacion);
        this.setAutor(autor);
        this.setMascotaElegida(mascotaElegida);
        this.setRespuestasOrganizacion(respuestasOrganizacion);
        this.personasInteresadas = new ArrayList<>();
    }

    @Override
    public void mostrarPublicacion() {
        this.mascotaElegida.mostrarDatosMascota();
        this.duenioActual.mostrarDatosNoSensibles();
        for(Respuesta respuesta : respuestasOrganizacion) {
            respuesta.mostrarRespuesta();
        }
        System.out.println("Personas interesadas: " + personasInteresadas.size());
    }
}
