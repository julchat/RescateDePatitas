package domain.repositorios;

import domain.business.users.Contacto;
import domain.repositorios.daos.DAO;

public class RepositorioContactos extends Repositorio<Contacto> {

    public RepositorioContactos(DAO<Contacto> dao) { super(dao); }
}
