package domain.business.foto;

import domain.business.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dimensiones_foto")
public abstract class DimensionEstandar extends EntidadPersistente {
    private int ancho;
    private int alto;


    // Getters and Setters
    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}