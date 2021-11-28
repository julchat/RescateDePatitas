package domain.business.users;
/*
import domain.business.caracteristicas.Caracteristica;
import domain.business.organizaciones.Organizacion;
import domain.business.publicaciones.Pregunta;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.stream.Collectors;

@Entity
@Table(name = "administrador")
@DiscriminatorColumn(name = "administrador")
public class Administrador extends PersonaOrganizacion {

    // Constructor
    public Administrador() { }

    public void agregarCaracteristicaOrganizacion(Caracteristica caracteristica) {
        this.getOrganizacion().getCaracteristicasAdmitidas().add(caracteristica);
    }

    public boolean quitarCaracteristicaOrganizacion(String caracteristicaAEliminar) {

        // TODO: la mejor opcion es que te muestre todas las caracteristicas, y ahi uno elige la caracteristica a eliminar
        for(Caracteristica caracteristica : this.getOrganizacion().getCaracteristicasAdmitidas()) {
            System.out.println(caracteristica);
        }
        // Todo: elegir la caracteristica y escribirla tal cual, asi matchea y elimina la que coincide

        // Otra opcion
        if(this.getOrganizacion().getCaracteristicasAdmitidas().stream().anyMatch(caracteristica -> caracteristica.equals(caracteristicaAEliminar))) {
            this.getOrganizacion().getCaracteristicasAdmitidas().remove(this.getOrganizacion().getCaracteristicasAdmitidas().stream().filter(caracteristica -> caracteristica.equals(caracteristicaAEliminar)).collect(Collectors.toList()).get(0));
            return true;
        }
        else {
            return false;
        }
    }

    public void agregarPreguntaOrganizacion(String pregunta) {
        this.getOrganizacion().crearPregunta(pregunta);
    }

    public boolean quitarPreguntaOrganizacion(String preguntaAEliminar) {

        for(Pregunta pregunta : this.getOrganizacion().getPreguntasOrganizacion()) {
            System.out.println(pregunta.getPregunta());
        }

        if(this.getOrganizacion().getPreguntasOrganizacion().stream().anyMatch(pregunta -> pregunta.getPregunta().equals(preguntaAEliminar))) {
            this.getOrganizacion().getPreguntasOrganizacion().remove(this.getOrganizacion().getPreguntasOrganizacion().stream().filter(pregunta -> pregunta.getPregunta().equals(preguntaAEliminar)).collect(Collectors.toList()).get(0));
            return true;
        }
        else {
            return false;
        }
    }
}
*/