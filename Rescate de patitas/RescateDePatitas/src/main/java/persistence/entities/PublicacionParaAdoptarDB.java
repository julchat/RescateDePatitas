package persistence.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "publicacion_para_adopcion")
@DiscriminatorColumn(name = "para_adoptar")
public class PublicacionParaAdoptarDB extends PublicacionDB {

    @ElementCollection
    private List<String> comodidades;

    @ElementCollection
    private List<String> preferencias;


// Getters and Setters
    public List<String> getComodidades() { return comodidades; }

    public void setComodidades(List<String> comodidades) { this.comodidades = comodidades; }

    public List<String> getPreferencias() { return preferencias; }

    public void setPreferencias(List<String> preferencias) {
        this.preferencias = preferencias;
    }
}
