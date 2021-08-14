package domain.business;

public class Chapa {
    private int idChapa;
    private Mascota mascota;
    private Duenio dueño;

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

    public Duenio getDueño() {
        return dueño;
    }

    public void setDueño(Duenio dueño) {
        this.dueño = dueño;
    }

    public Chapa() {}

    public Chapa(int idChapa, Mascota mascota, Duenio dueño) {
        this.idChapa = idChapa;
        this.mascota = mascota;
        this.dueño = dueño;
    }


    public void generarQR() {}

}
