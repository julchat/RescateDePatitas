package database.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "caracteristica")
public class CaracteristicaDB extends EntidadPersistente {

    private String caracteristica;


// Getters and Setters
    public String getCaracteristica() { return caracteristica; }

    public void setCaracteristica(String caracteristica) { this.caracteristica = caracteristica; }
}
