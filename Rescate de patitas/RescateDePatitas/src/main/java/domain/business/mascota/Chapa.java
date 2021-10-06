package domain.business;

import domain.business.mascota.Mascota;
import domain.business.users.Duenio;

public class Chapa {
    private int idChapa;
    private Mascota mascota;
    private Duenio duenio;

    public int getIdChapa() {
        return idChapa;
    }

    public void setIdChapa(int idChapa) {
        this.idChapa = idChapa;
    }

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

    public Chapa(int idChapa, Mascota mascota, Duenio duenio) {
        this.idChapa = idChapa;
        this.mascota = mascota;
        this.duenio = duenio;
    }


    public void generarQR() {}

}
