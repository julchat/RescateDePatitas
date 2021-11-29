package domain.business.publicaciones;

import domain.business.mascota.MascotaPerdida;
import domain.business.ubicacion.Lugar;
import domain.business.users.Persona;

import javax.persistence.*;

@Entity
@Table(name = "publicacion_mascota_perdida")
@DiscriminatorColumn(name = "mascota_perdida")
public class PublicacionMascotaPerdida extends Publicacion {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mascotaRescatada")
    private MascotaPerdida mascotaRescatada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lugarDeTransito")
    private Lugar lugarDeTransito;


    // Getters and Setters
    public MascotaPerdida getMascotaRescatada() { return mascotaRescatada; }

    public void setMascotaRescatada(MascotaPerdida mascotaRescatada) {
        this.mascotaRescatada = mascotaRescatada;
    }

    public Lugar getLugarDeTransito() { return lugarDeTransito; }

    public void setLugarDeTransito(Lugar lugarDeTransito) { this.lugarDeTransito = lugarDeTransito; }


    // Metodos
    public void crearPublicacion(Persona autor, MascotaPerdida mascotaRescatada) {
        super.crearPublicacion();
        this.setAutor(autor);
        this.setMascotaRescatada(mascotaRescatada);
        this.setLugarDeTransito(mascotaRescatada.getLugarDeTransito());
    }
}
