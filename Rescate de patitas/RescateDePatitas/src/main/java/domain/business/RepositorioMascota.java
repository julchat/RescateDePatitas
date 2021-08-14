package domain.business;

import domain.business.Mascota;

import java.util.List;

public class RepositorioMascota {
    private List<Mascota> mascotas;

    public void agregarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    public void quitarMascota(Mascota mascota) {
        mascotas.remove(mascota);
    }

   /* public Mascota buscarMascota(Mascota mascotaBuscada) {
        return mascotas.stream().filter(mascota -> mascota == mascotaBuscada).findFirst().get();
    }*/

    /*public Mascota buscarMascota(Chapa chapaMascota) {
        // Todo de algun repositorio de chapas, buscar la que contenga los datos de esta chapita
        //  O sino, a partir de esta chapita, podemos ubicar al duenio y asi poder notificarle
        return null;
    }*/

}

