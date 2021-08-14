package domain.business.foto;

public abstract class DimensionEstandar{
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