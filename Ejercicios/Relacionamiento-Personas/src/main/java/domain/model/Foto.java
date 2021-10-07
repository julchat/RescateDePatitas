package domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "foto")
public class Foto extends EntidadPersistente {

    private double ancho;
    private double altura;


    // Getters and Setters
    public double getAncho() { return ancho; }

    public void setAncho(double ancho) { this.ancho = ancho; }

    public double getAltura() { return altura; }

    public void setAltura(double altura) { this.altura = altura; }

    public Foto() {}
}
