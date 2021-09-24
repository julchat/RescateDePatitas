package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "Chapas")
public class ChapaDB extends EntidadPersistente {

    // Tal vez esto es medio redundante, y nos quedamos solamente con el ID de base
    @Column(name = "ID_Chapa")
    private int idChapa;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Mascota ID")
    private MascotaDB mascota;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Dueño ID")
    private DuenioDB duenio;


// Getters and Setters
    public int getIdChapa() { return idChapa; }

    public void setIdChapa(int idChapa) { this.idChapa = idChapa; }

    public MascotaDB getMascota() { return mascota; }

    public void setMascota(MascotaDB mascota) { this.mascota = mascota; }

    public DuenioDB getDuenio() { return duenio; }

    public void setDuenio(DuenioDB duenio) { this.duenio = duenio; }
}
