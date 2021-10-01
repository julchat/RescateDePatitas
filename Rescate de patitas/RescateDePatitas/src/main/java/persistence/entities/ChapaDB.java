package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "chapa")
public class ChapaDB extends EntidadPersistente {

    @Column(name = "chapa_id")
    private int idChapa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mascota_id")
    private MascotaDB mascota;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "duenio_id")
    private DuenioDB duenio;


// Getters and Setters
    public int getIdChapa() { return idChapa; }

    public void setIdChapa(int idChapa) { this.idChapa = idChapa; }

    public MascotaDB getMascota() { return mascota; }

    public void setMascota(MascotaDB mascota) { this.mascota = mascota; }

    public DuenioDB getDuenio() { return duenio; }

    public void setDuenio(DuenioDB duenio) { this.duenio = duenio; }
}
