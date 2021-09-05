package domain.business.publicaciones;

import domain.business.MascotaPerdida;
import domain.business.Rescatista;

public class BusquedaMascotaPerdida extends TipoPublicacion {
    private MascotaPerdida mascotaRescatada;
    private Rescatista rescatista;

    // Getters and Setters
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


    // Metodos
    public void proponerResidencia() {}

    @Override
    public void mostrarDatos() {
        this.mascotaRescatada.mostrarMascota();
        this.rescatista.mostrarDatosNoSensibles();
    }
}
