package domain.business.mascota;

import domain.business.EntidadPersistente;
import domain.business.notificaciones.Notificador;
import domain.business.users.Persona;

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
    private Persona duenio;


    // Getters and Setters
    public String getHash() { return hash; }

    public void setHash(String hash) { this.hash = hash; }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Persona getDuenio() {
        return duenio;
    }

    public void setDuenio(Persona duenio) {
        this.duenio = duenio;
    }


    // Metodos
    public Chapa() {}

    public Chapa(Persona duenio, Mascota mascota) throws IOException {
        this.setDuenio(duenio);
        this.setMascota(mascota);
    }

    public void generarQR(int id) throws IOException {
        QRCode qrGenerator = new QRCode();
        qrGenerator.crearQR("http://localhost:9000/reportar-mascota/" + id, "ChapitaN°" + id + ".png");
    //Todo: agregar un Hash en vez del ID de la chapita

        // tal vez ese codigo QR se guarda en un repositorio, o directamente queda asi
    }

    public void notificarDuenio(Persona rescatista) {
        // Notifica al Dueño de la Mascota
        duenio.getFormasDeNotificacion().forEach(notificacion -> notificacion.notificarMascotaEncontrada(duenio, rescatista, mascota));
        // Notifica a cada uno de los Contactos que haya agregado la persona
        if(!duenio.getContactos().isEmpty()) {
            duenio.getContactos().forEach(contacto -> contacto.getFormasDeNotificacion().forEach(notificacion -> notificacion.notificarMascotaEncontrada(duenio, rescatista, mascota)));
        }
    }
}
