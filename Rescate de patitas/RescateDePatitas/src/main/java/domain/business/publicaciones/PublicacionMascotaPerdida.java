package domain.business.publicaciones;

import domain.business.*;

import java.time.LocalDate;

public class PublicacionMascotaPerdida extends Publicacion {
    private MascotaPerdida mascotaRescatada;
    private Lugar lugarDeTransito;


    // Getters and Setters
    public MascotaPerdida getMascotaRescatada() { return mascotaRescatada; }

    public void setMascotaRescatada(MascotaPerdida mascotaRescatada) {
        this.mascotaRescatada = mascotaRescatada;
    }

    public Lugar getLugarDeTransito() { return lugarDeTransito; }

    public void setLugarDeTransito(Lugar lugarDeTransito) { this.lugarDeTransito = lugarDeTransito; }


    // Metodos
    public void crearPublicacion(EstadoPublicacion estadoPublicacion, Rescatista autor, MascotaPerdida mascotaRescatada) {
        super.crearPublicacion(estadoPublicacion, autor);
        this.setAutor(autor);
        this.setMascotaRescatada(mascotaRescatada);
        this.setLugarDeTransito(mascotaRescatada.getLugarDeTransito());
    }

    @Override
    public void mostrarPublicacion() {
        this.mascotaRescatada.mostrarMascota();
        this.getAutor().mostrarDatosNoSensibles();
    }


}
