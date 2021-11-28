package domain.business.users;
/*
import domain.business.ubicacion.Lugar;
import domain.business.mascota.MascotaPerdida;
import domain.business.publicaciones.PublicacionMascotaPerdida;
import domain.repositorios.RepositorioPublicaciones;
import domain.repositorios.factories.FactoryRepositorioPublicaciones;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rescatista")
@DiscriminatorValue("Rescatista")
public class Rescatista extends Persona {

    private boolean puedeAlojarMascota;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Column(name = "mascotasAlojadas")
    private List<MascotaPerdida> mascotasAlojadas = new ArrayList<>();


    // Getters and Setters
    public boolean isPuedeAlojarMascota() {
        return puedeAlojarMascota;
    }

    public void setPuedeAlojarMascota(boolean puedeAlojarMascota) {
        this.puedeAlojarMascota = puedeAlojarMascota;
    }

    public List<MascotaPerdida> getMascotasAlojadas() { return mascotasAlojadas; }

    public void setMascotasAlojadas(List<MascotaPerdida> mascotasAlojadas) { this.mascotasAlojadas = mascotasAlojadas; }

    public Rescatista() { }

    // Metodos
    public void alojarMascota(MascotaPerdida mascotaPerdida) {
        Lugar nuevoLugar = new Lugar();
        mascotaPerdida.setLugarDeTransito(nuevoLugar.mapearLugar(this.getDomicilio()));
        mascotasAlojadas.add(mascotaPerdida);
    }

}
*/