package persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Caracteristicas")
public class CaracteristicaDB extends EntidadPersistente {

    @Column(name = "Nombre Característica")
    private String nombreCaracteristica;

    @Column(name = "Descripción")
    private String valorElegido;
}
