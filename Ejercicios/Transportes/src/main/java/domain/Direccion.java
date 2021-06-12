package domain;

public class Direccion {
    private String direccion;
    private String provincia;
    private String localidad;
    private int codigoPostal;

    // Getters and Setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

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

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    // Constructor
    public Direccion(String direccion, String provincia, String localidad, int codigoPostal) {
        this.direccion = direccion;
        this.provincia = provincia;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
    }

    // Compara localia por el Codigo Postal
    public boolean mismaLocalidad(Direccion otraDireccion) {
        return this.codigoPostal == otraDireccion.getCodigoPostal();
    }
}
