package persistence.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Duenio")
public class DuenioDB extends PersonaDB{

    @OneToOne
    @Column(name = "Domicilio")
    private DomicilioDB domicilio;

    @OneToMany(mappedBy = "Duenio", cascade = {CascadeType.ALL})
    @Column(name = "Mascotas")
    private List<MascotaDB> mascotas;

// Getters and Setters
    public DomicilioDB getDomicilio() { return domicilio; }

    public void setDomicilio(DomicilioDB domicilio) { this.domicilio = domicilio; }

    public List<MascotaDB> getMascotas() { return mascotas; }

    public void setMascotas(List<MascotaDB> mascotas) { this.mascotas = mascotas; }
}
