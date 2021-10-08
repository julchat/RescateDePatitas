package domain.business.publicaciones;

import domain.business.mascota.MascotaPerdida;
import domain.business.ubicacion.Lugar;
import domain.business.users.Rescatista;

public class PublicacionMascotaPerdida extends Publicacion {
    private MascotaPerdida mascotaRescatada;
    private Lugar lugarDeTransito;
    //private Persona autor;


    // Getters and Setters
    public MascotaPerdida getMascotaRescatada() { return mascotaRescatada; }

    public void setMascotaRescatada(MascotaPerdida mascotaRescatada) {
        this.mascotaRescatada = mascotaRescatada;
    }

    public Lugar getLugarDeTransito() { return lugarDeTransito; }

    public void setLugarDeTransito(Lugar lugarDeTransito) { this.lugarDeTransito = lugarDeTransito; }


    // Metodos
    public void crearPublicacion(Rescatista autor, MascotaPerdida mascotaRescatada) {
        super.crearPublicacion(new Pendiente());
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
