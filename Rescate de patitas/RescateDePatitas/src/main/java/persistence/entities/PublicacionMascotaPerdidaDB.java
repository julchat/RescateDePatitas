package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "publicacion_mascota_perdida")
@DiscriminatorColumn(name = "publicacion_mascota_perdida")
public class PublicacionMascotaPerdidaDB extends PublicacionDB {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mascota_perdida_id")
    private MascotaPerdidaDB mascotaRescatada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lugar_de_transito_id")
    private LugarDB lugarDeTransito;


// Getters and Setters
    public MascotaPerdidaDB getMascotaRescatada() { return mascotaRescatada; }

    public void setMascotaRescatada(MascotaPerdidaDB mascotaRescatada) { this.mascotaRescatada = mascotaRescatada; }

    public LugarDB getLugarDeTransito() { return lugarDeTransito; }

    public void setLugarDeTransito(LugarDB lugarDeTransito) { this.lugarDeTransito = lugarDeTransito; }
}
