package database.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publicacion_mascota_en_adopcion")
@DiscriminatorColumn(name = "publicacion_mascota_en_adopcion")
public class PublicacionMascotaAdopcionDB extends PublicacionDB {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "duenio_id")
    private DuenioDB duenioActual;

    @OneToOne
    @JoinColumn(name = "mascota_id")
    private MascotaDB mascotaElegida;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "respuestas")
    private List<RespuestaDB> respuestas = new ArrayList<>();


// Getters and Setters
    public DuenioDB getDuenioActual() { return duenioActual; }

    public void setDuenioActual(DuenioDB duenioActual) { this.duenioActual = duenioActual; }

    public MascotaDB getMascotaElegida() { return mascotaElegida; }

    public void setMascotaElegida(MascotaDB mascotaElegida) { this.mascotaElegida = mascotaElegida; }

    public List<RespuestaDB> getRespuestas() { return respuestas; }

    public void setRespuestas(List<RespuestaDB> respuestas) { this.respuestas = respuestas; }
}
