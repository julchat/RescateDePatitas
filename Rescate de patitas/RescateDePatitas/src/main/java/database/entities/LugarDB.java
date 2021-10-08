package database.entities;

import javax.persistence.*;

@Entity
@Table(name = "Lugar")
public class LugarDB extends EntidadPersistente{

    private String direccion;

    @OneToOne
    @JoinColumn(name = "ubicacion_id")
    private UbicacionDB ubicacion;


// Getters and Setters
    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public UbicacionDB getUbicacion() { return ubicacion; }

    public void setUbicacion(UbicacionDB ubicacion) { this.ubicacion = ubicacion; }
}