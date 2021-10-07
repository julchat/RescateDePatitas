package domain.business.mascota;

import domain.business.users.Duenio;
import domain.business.users.Rescatista;

public class Chapa {
    private Mascota mascota;
    private Duenio duenio;

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

    public Chapa() {}

    public Chapa(Mascota mascota, Duenio duenio) {
        this.mascota = mascota;
        this.duenio = duenio;
    }


    public void generarQR() {
        // TODO: genera formulario del Rescatista
        // Rescatista rescatista = new Rescatista();
       // this.notificarDuenio(rescatista);
    }

    public void notificarDuenio(Rescatista rescatista) {
        this.duenio.notificarDuenio(rescatista, mascota);
    }

}
