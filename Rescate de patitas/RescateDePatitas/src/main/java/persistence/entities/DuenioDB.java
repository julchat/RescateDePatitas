package persistence.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Crear una tabla nueva de Dueño, que conoce a una persona y tiene los nuevos datos
// Relacion 1 a 1 entre Dueño y Persona

@Entity
@Table(name = "Duenio")
public class DuenioDB extends PersonaDB {

    @OneToOne
    @JoinColumn(name = "Domicilio")
    private DomicilioDB domicilio;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Mascotas")
    private List<MascotaDB> mascotas = new ArrayList<MascotaDB>();

    // Getters and Setters
    public DomicilioDB getDomicilio() { return domicilio; }

    public void setDomicilio(DomicilioDB domicilio) { this.domicilio = domicilio; }

    public List<MascotaDB> getMascotas() { return mascotas; }

    public void setMascotas(List<MascotaDB> mascotas) { this.mascotas = mascotas; }
}