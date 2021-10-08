package domain.business.users;

import domain.business.organizaciones.Organizacion;
import domain.business.publicaciones.Pregunta;

import java.util.stream.Collectors;

public class Administrador extends Persona {
    private Organizacion organizacion;

    // Getters and Setters
    public Organizacion getOrganizacion() { return organizacion; }

    public void setOrganizacion(Organizacion organizacion) { this.organizacion = organizacion; }

    // Constructor
    public Administrador() { }

    public void agregarCaracteristicaOrganizacion(String caracteristica) {
        organizacion.getCaracteristicasAdmitidas().add(caracteristica);
    }

    public boolean quitarCaracteristicaOrganizacion(String caracteristicaAEliminar) {

        // TODO: la mejor opcion es que te muestre todas las caracteristicas, y ahi uno elige la caracteristica a eliminar
        for(String caracteristica : organizacion.getCaracteristicasAdmitidas()) {
            System.out.println(caracteristica);
        }
        // Todo: elegir la caracteristica y escribirla tal cual, asi matchea y elimina la que coincide

        // Otra opcion
        if(organizacion.getCaracteristicasAdmitidas().stream().anyMatch(caracteristica -> caracteristica.equals(caracteristicaAEliminar))) {
            organizacion.getCaracteristicasAdmitidas().remove(organizacion.getCaracteristicasAdmitidas().stream().filter(caracteristica -> caracteristica.equals(caracteristicaAEliminar)).collect(Collectors.toList()).get(0));
            return true;
        }
        else {
            return false;
        }
    }

    public void agregarPreguntaOrganizacion(String pregunta) {
        organizacion.crearPregunta(pregunta);
    }

    public boolean quitarPreguntaOrganizacion(String preguntaAEliminar) {

        for(Pregunta pregunta : organizacion.getPreguntasOrganizacion()) {
            System.out.println(pregunta.getPregunta());
        }

        if(organizacion.getPreguntasOrganizacion().stream().anyMatch(pregunta -> pregunta.getPregunta().equals(preguntaAEliminar))) {
            organizacion.getPreguntasOrganizacion().remove(organizacion.getPreguntasOrganizacion().stream().filter(pregunta -> pregunta.getPregunta().equals(preguntaAEliminar)).collect(Collectors.toList()).get(0));
            return true;
        }
        else {
            return false;
        }
    }
}
