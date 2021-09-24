package persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Lugar")
public class LugarDB extends EntidadPersistente{

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Longitud")
    private double longitud;

    @Column(name = "Latitud")
    private double latitud;


// Getters and Setters
    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double getLongitud() { return longitud; }

    public void setLongitud(double longitud) { this.longitud = longitud; }

    public double getLatitud() { return latitud; }

    public void setLatitud(double latitud) { this.latitud = latitud; }
}
