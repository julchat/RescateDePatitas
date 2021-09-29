package domain.business.foto;

import java.util.HashMap;

public class Foto {
    private int alto;
    private int ancho;
    private HashMap<Posicion, Color> pixeles = new HashMap<>();

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

// Metodos
    public void normalizarA(DimensionEstandar dimension) {}

    public void resetearPixeles(){ pixeles = new HashMap<>(); }

    public int armarRGB(int red, int green, int blue){
        return 0;
    }

   /* public int[] desarmarRGB(int colorRGB){
        return [0,0,0]
    }*/
}
