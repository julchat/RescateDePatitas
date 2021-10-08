package database.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dimensiones_foto")
public class DimensionesDB extends EntidadPersistente {
    private int ancho;
    private int alto;


// Getters and Setters
    public int getAncho() { return ancho; }

    public void setAncho(int ancho) { this.ancho = ancho; }

    public int getAlto() { return alto; }

    public void setAlto(int alto) { this.alto = alto; }
}
