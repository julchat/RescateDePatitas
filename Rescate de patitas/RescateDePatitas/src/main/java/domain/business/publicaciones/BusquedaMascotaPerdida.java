package domain.business.publicaciones;

import domain.business.MascotaPerdida;
import domain.business.Rescatista;

public class BusquedaMascotaPerdida extends TipoPublicacion {
    private MascotaPerdida mascotaRescatada;
    private Rescatista rescatista;

    public MascotaPerdida getMascotaRescatada() {
        return mascotaRescatada;
    }

    public void setMascotaRescatada(MascotaPerdida mascotaRescatada) {
        this.mascotaRescatada = mascotaRescatada;
    }

    public Rescatista getRescatista() {
        return rescatista;
    }

    public void setRescatista(Rescatista rescatista) {
        this.rescatista = rescatista;
    }


    public void proponerResidencia() {}
}
