package domain.repositorios;

import domain.business.users.Duenio;
import domain.repositorios.daos.DAO;

public class RepositorioDuenio extends Repositorio<Duenio> {
    public RepositorioDuenio(DAO<Duenio> dao) { super(dao); }
}
