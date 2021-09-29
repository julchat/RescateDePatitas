package persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "caracteristica")
public class CaracteristicaDB extends EntidadPersistente {

    @Column(name = "nombre_caracteristica")
    private String caracteristica;

// Getters and Setters
    public String getCaracteristica() { return caracteristica; }

    public void setCaracteristica(String caracteristica) { this.caracteristica = caracteristica; }
}
