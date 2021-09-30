package persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ubicacion")
public class UbicacionDB extends EntidadPersistente {

    private double latitud;
    private double longitud;
}
