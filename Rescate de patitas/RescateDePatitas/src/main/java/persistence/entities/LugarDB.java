package persistence.entities;

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
}