package domain.repositorios;

import domain.business.mascota.Mascota;
import domain.business.mascota.MascotaPerdida;
import domain.repositorios.daos.DAO;

import java.util.List;

public class RepositorioMascotas extends Repositorio<Mascota>{

    public RepositorioMascotas(DAO<Mascota> dao) { super(dao); }


    public Mascota buscarMascotaChapita(int idMascota) {
        return this.dao.buscar(idMascota);
    }

  /*  public Mascota buscarMascota(Chapa chapaMascota) {
        // Todo de algun repositorio de chapas, buscar la que contenga los datos de esta chapita
        //  O sino, a partir de esta chapita, podemos ubicar al duenio y asi poder notificarle
        return null;
    }*/

}

