package domain.repositorios;

import domain.business.users.Persona;
import domain.repositorios.daos.DAO;

public class RepositorioPersonas extends Repositorio<Persona> {
    public RepositorioPersonas(DAO<Persona> dao) { super(dao); }
}
