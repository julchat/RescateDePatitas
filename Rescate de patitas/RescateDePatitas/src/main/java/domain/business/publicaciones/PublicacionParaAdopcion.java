package domain.business.publicaciones;

import domain.business.users.Persona;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "publicacion_para_adopcion")
@DiscriminatorColumn(name = "para_adopcion")
public class PublicacionParaAdopcion extends Publicacion {

    @ElementCollection
    private List<String> comodidades;

    @ElementCollection
    private List<String> preferencias;


    // Getters and Setters
    public void setComodidades(List<String> comodidades) { this.comodidades = comodidades; }

    public void agregarComodidad(String nuevaComodidad) {
        this.comodidades.add(nuevaComodidad);
    }

    public void setPreferencias(List<String> preferencias) { this.preferencias = preferencias; }

    public void agregarPreferencia(String nuevaPreferencia) {
        this.preferencias.add(nuevaPreferencia);
    }

    public List<String> obtenerComodidades() {
        return this.comodidades;
    }

    public List<String> obtenerPreferencias() {
        return this.preferencias;
    }


    // Métodos
    public void crearPublicacion(Persona autor, List<String> comodidades, List<String> preferencias) {
        super.crearPublicacion();
        this.setAutor(autor);
        this.setComodidades(comodidades);
        this.setPreferencias(preferencias);
    }
}
