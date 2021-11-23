package json;

import domain.business.mascota.Mascota;

import java.util.List;

public class PetsLists {

    public List<Mascota> mascotas;

    public PetsLists(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
