package database.entities;

import javax.persistence.*;

@Entity
@Table(name = "chapa")
public class ChapaDB extends EntidadPersistente {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mascota_id")
    private MascotaDB mascota;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "duenio_id")
    private DuenioDB duenio;


// Getters and Setters
    public MascotaDB getMascota() { return mascota; }

    public void setMascota(MascotaDB mascota) { this.mascota = mascota; }

    public DuenioDB getDuenio() { return duenio; }

    public void setDuenio(DuenioDB duenio) { this.duenio = duenio; }
}
