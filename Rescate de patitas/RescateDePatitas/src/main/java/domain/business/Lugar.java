package domain.business;

public class Lugar {
    private String direccion;
    private double longitud;
    private double latitud;

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

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


    // Constructor
    public Lugar() {}


    // Metodos
    public Lugar mapearLugar(Domicilio domicilio) {
        String direccion = domicilio.getCalle() + " " + domicilio.getNumero() + ", " + domicilio.getLocalidad() + ", " + domicilio.getProvincia();
        this.setDireccion(direccion);
        this.setLatitud(domicilio.getUbicacion().getLatitud());
        this.setLongitud(domicilio.getUbicacion().getLongitud());
        return this;
    }
}
