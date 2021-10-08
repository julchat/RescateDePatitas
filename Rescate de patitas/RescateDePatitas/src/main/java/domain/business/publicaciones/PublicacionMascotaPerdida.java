package domain.business.publicaciones;

import domain.business.mascota.MascotaPerdida;
import domain.business.ubicacion.Lugar;
import domain.business.users.Rescatista;

import javax.persistence.*;

@Entity
@Table(name = "publicacion_mascota_perdida")
@DiscriminatorColumn(name = "publicacion_mascota_perdida")
public class PublicacionMascotaPerdida extends Publicacion {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mascota_perdida_id")
    private MascotaPerdida mascotaRescatada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lugar_de_transito_id")
    private Lugar lugarDeTransito;


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
