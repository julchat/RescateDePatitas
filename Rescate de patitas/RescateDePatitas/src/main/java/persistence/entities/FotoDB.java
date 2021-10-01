package persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "foto")
public class FotoDB extends EntidadPersistente {
    private int alto;
    private int ancho;
    private String ubicacion_foto;


// Getters and Setters
    public int getAlto() { return alto; }

    public void setAlto(int alto) { this.alto = alto; }

    public int getAncho() { return ancho; }

    public void setAncho(int ancho) { this.ancho = ancho; }

    public String getUbicacion_foto() { return ubicacion_foto; }

    public void setUbicacion_foto(String ubicacion_foto) { this.ubicacion_foto = ubicacion_foto; }
}
