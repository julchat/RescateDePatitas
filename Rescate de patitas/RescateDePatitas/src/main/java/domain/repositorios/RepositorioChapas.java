package domain.repositorios;

import domain.business.mascota.Chapa;
import domain.business.mascota.Mascota;
import domain.repositorios.daos.DAO;

import java.util.List;

public class RepositorioChapas extends Repositorio<Chapa> {
    public RepositorioChapas(DAO<Chapa> dao) { super(dao); }

    public Chapa buscarChapa(int idChapa){
        return this.dao.buscar(idChapa);
    }

}
