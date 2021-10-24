package domain.repositorios;

import domain.business.mascota.MascotaPerdida;
import domain.repositorios.daos.DAO;

public class RepositorioMascotaPerdida extends Repositorio<MascotaPerdida> {
    public RepositorioMascotaPerdida(DAO<MascotaPerdida> dao) { super(dao); }
}
