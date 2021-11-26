package domain.repositorios;

import domain.business.publicaciones.PublicacionMascotaPerdida;
import domain.repositorios.daos.DAO;

public class RepositorioPubliMascotaPerdida extends Repositorio<PublicacionMascotaPerdida>{
    public RepositorioPubliMascotaPerdida(DAO<PublicacionMascotaPerdida> dao) { super(dao); }
}
