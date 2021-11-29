package domain.business.ubicacion;


import domain.business.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lugar")
public class Lugar extends EntidadPersistente {

    private String direccion;
    private double longitud;
    private double latitud;


    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double getLongitud() { return longitud; }

    public void setLongitud(double longitud) { this.longitud = longitud; }

    public double getLatitud() { return latitud; }

    public void setLatitud(double latitud) { this.latitud = latitud; }


    // Constructor
    public Lugar() {}

    public Lugar(String direccion, double longitud, double latitud) {
        this.direccion = direccion;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    // Metodos
    public Lugar mapearLugar(Domicilio domicilio) {
        String direccion = domicilio.getCalle() + " " + domicilio.getNumeracion() + ", " + domicilio.getLocalidad() + ", " + domicilio.getProvincia();
        this.setDireccion(direccion);
        //this.setUbicacion(new Ubicacion(domicilio.getUbicacion().getLongitud(), domicilio.getUbicacion().getLatitud()));
        return this;
    }
}
