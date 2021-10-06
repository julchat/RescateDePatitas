package domain.business;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "administrador")
@DiscriminatorColumn(name = "administrador")
public class Administrador extends Actor {

    // Metodos
    public List<Actor> reporteListadoPersonas() {
        List<Actor> reportePersonas = new ArrayList<>();
        return reportePersonas;
    }

    public List<Autorizacion> reporteListadoAutorizaciones() {
        List<Autorizacion> reporteAutorizaciones = new ArrayList<>();
        return reporteAutorizaciones;
    }
}
