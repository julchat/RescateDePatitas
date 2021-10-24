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
    @JoinColumn(name = "mascota")
    private Mascota mascota;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "duenio")
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

    public Chapa(Duenio duenio, Mascota mascota) throws IOException {
        this.setDuenio(duenio);
        this.setMascota(mascota);
        this.generarQR();

        // esta chapa tendria que agregarse al RepositorioDeChapas
        // Tambien, esta chapita deberia crearse con su ID correspondiente, Nombre Mascota y Codigo QR
        //      y enviarse al Domicilio del Duenio
    }

    public void generarQR() throws IOException {
        QRCode qrGenerator = new QRCode();
        qrGenerator.crearQR("http://localhost:9000/formulario-mascota-perdida/" + "{id}" + this.getId(), "ChapitaN°" + this.getId() + ".png");

        // tal vez ese codigo QR se guarda en un repositorio, o directamente queda asi
    }

    //formulario-mascota-perdida/{id=182}


    public void notificarDuenio(Rescatista rescatista) {
        this.duenio.notificarDuenio(rescatista, mascota);
    }
}
