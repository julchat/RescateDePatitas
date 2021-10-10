package domain.business.mascota;

import com.google.zxing.WriterException;
import domain.business.EntidadPersistente;
import domain.business.users.Duenio;
import domain.business.users.Rescatista;

import javax.persistence.*;
import java.io.IOException;


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

    public void generarQR() throws WriterException, IOException {
        QRCode qrGenerator = new QRCode();

        qrGenerator.crearQR("URL AL FORMULARIO PERO CON ID DE CHAPITA" + "?id=" + this.getId(), "ChapitaN°" + this.getId() + ".png");
        // Rescatista rescatista = new Rescatista();
       // this.notificarDuenio(rescatista);
    }

    //formulario-mascota-perdida-chapita?id=182




    public void notificarDuenio(Rescatista rescatista) {
        this.duenio.notificarDuenio(rescatista, mascota);
    }

}
