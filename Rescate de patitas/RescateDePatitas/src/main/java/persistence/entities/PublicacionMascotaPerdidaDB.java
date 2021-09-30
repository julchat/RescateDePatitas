package persistence.entities;

import domain.business.Lugar;
import domain.business.MascotaPerdida;

import javax.persistence.*;

@Entity
@Table(name = "publicacion mascota perdida")
@DiscriminatorColumn(name = "mascota_perdida")
public class PublicacionMascotaPerdidaDB extends PublicacionDB {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mascota_perdida_id")
    private MascotaPerdidaDB mascotaRescatada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lugar_de_transito_id")
    private LugarDB lugarDeTransito;
}
