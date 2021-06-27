package domain.business;

public class Domicilio {
    private String provincia;
    private String localidad;
    private String barrio;
    private String calle;
    private int numero;
    private int latitud;
    private int longitud;

    // Getters and Setters
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }


    // Constructor
    public Domicilio() {}

    public Domicilio(String provincia, String localidad, String barrio, String calle, int numero, int latitud, int longitud) {
        this.provincia = provincia;
        this.localidad = localidad;
        this.barrio = barrio;
        this.calle = calle;
        this.numero = numero;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
