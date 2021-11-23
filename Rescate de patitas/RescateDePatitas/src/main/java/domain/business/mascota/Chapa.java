package domain.business.mascota;

import domain.business.EntidadPersistente;
import domain.business.users.Duenio;
import domain.business.users.Rescatista;

import javax.persistence.*;
import java.io.IOException;


@Entity
@Table(name = "chapa")
public class Chapa extends EntidadPersistente {

    @Transient
    private String hash;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "mascota")
    private Mascota mascota;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "duenio")
    private Duenio duenio;


    // Getters and Setters
    public String getHash() { return hash; }

    public void setHash(String hash) { this.hash = hash; }

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

    public Chapa(Duenio duenio, Mascota mascota) throws IOException {
        this.setDuenio(duenio);
        this.setMascota(mascota);
        this.generarQR();
    }

    public void generarQR() throws IOException {
        QRCode qrGenerator = new QRCode();
        qrGenerator.crearQR("http://localhost:9000/reportar-mascota/" + this.getId(), "ChapitaN°" + this.getId() + ".png");
    //Todo: agregar un Hash en vez del ID de la chapita

        // tal vez ese codigo QR se guarda en un repositorio, o directamente queda asi
    }

    public void notificarDuenio(Rescatista rescatista) {
        this.duenio.notificarDuenio(rescatista, mascota);
    }
}
