package domain.business;

public class Ubicacion {
    private double longitud;
    private double latitud;

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }


    public Ubicacion() { }

    public Ubicacion(double longitud, double latitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }
}
