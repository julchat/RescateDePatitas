package domain.business.caracteristicas;

import domain.business.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "caracteristica")
public class Caracteristica extends EntidadPersistente {

    private String caracteristica;
    private boolean esVisible;

    // Getters and Setters
    public String getCaracteristica() { return caracteristica; }

    public void setCaracteristica(String caracteristica) { this.caracteristica = caracteristica; }

    public boolean isEsVisible() { return esVisible; }

    public void setEsVisible(boolean esVisible) { this.esVisible = esVisible; }
}
