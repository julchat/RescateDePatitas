package domain.model;

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
        Sistema miSistema = Sistema.getInstance();
        List<Actor> reportePersonas = new ArrayList<>();

        for(User user: miSistema.getUsuarios()){
            reportePersonas.add(user.getActor());
        }

        return reportePersonas;
    }

    public List<Autorizacion> reporteListadoAutorizaciones() {
        Sistema miSistema = Sistema.getInstance();
        List<Autorizacion> reporteAutorizaciones = new ArrayList<>();

        for(Autorizacion autorizacion : miSistema.getAutorizaciones()){
            reporteAutorizaciones.add(autorizacion);
        }

        return reporteAutorizaciones;
    }
}
