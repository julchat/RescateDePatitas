package domain.repositorios;

import domain.business.caracteristicas.Caracteristica;
import domain.repositorios.daos.DAO;

public class RepositorioCaracteristicas extends Repositorio<Caracteristica>{
    public RepositorioCaracteristicas(DAO<Caracteristica> dao) { super(dao); }
}
