package domain.business.mascota;

import domain.business.EntidadPersistente;
import domain.business.users.Duenio;
import domain.business.users.Rescatista;

import javax.persistence.*;

@Entity
@Table(name = "chapa")
public class Chapa extends EntidadPersistente {

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "duenio_id")
    private Duenio duenio;


    // Getters and Setters
    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public void setDuenio(Duenio duenio) {
        this.duenio = duenio;
    }


    // Metodos
    public Chapa() {}

    public void generarQR() {
        // TODO: genera formulario del Rescatista
        // Rescatista rescatista = new Rescatista();
       // this.notificarDuenio(rescatista);
    }

    public void notificarDuenio(Rescatista rescatista) {
        this.duenio.notificarDuenio(rescatista, mascota);
    }

}
