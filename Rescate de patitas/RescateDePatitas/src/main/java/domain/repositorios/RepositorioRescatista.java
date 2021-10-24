package domain.repositorios;

import domain.business.users.Rescatista;
import domain.repositorios.daos.DAO;

public class RepositorioRescatista extends Repositorio<Rescatista> {
    public RepositorioRescatista(DAO<Rescatista> dao) { super(dao); }
}
