package domain.business.foto;

import domain.business.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashMap;

@Entity
@Table(name = "foto")
public class Foto extends EntidadPersistente {
    private int alto;
    private int ancho;
    private String ubicacionFoto;

    @Transient
    private HashMap<Posicion, Color> pixeles = new HashMap<>();

    @Transient
    public byte[] imagenByteArray;

    @Transient
    private int nivelDeCalidad;


    // Getters and Setters
    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public HashMap<Posicion, Color> getPixeles() { return pixeles; }

    public void setPixeles(HashMap<Posicion, Color> pixeles) { this.pixeles = pixeles; }

    public String getUbicacionFoto() { return ubicacionFoto; }

    public void setUbicacionFoto(String ubicacionFoto) { this.ubicacionFoto = ubicacionFoto; }


    // Metodos
    public Foto() {}

    public void normalizarA(DimensionEstandar dimension) {
        // TODO: deberia cambiar la resolucion de la foto?
        this.setAlto(dimension.getAlto());
        this.setAncho(dimension.getAncho());
    }

    public void resetearPixeles(){ pixeles = new HashMap<>(); }

    public int armarRGB(int red, int green, int blue){
        return 0;
    }

   /* public int[] desarmarRGB(int colorRGB){
        return [0,0,0]
    }*/
}
