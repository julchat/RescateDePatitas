package domain.repositories.factories;

import domain.business.Persona;
import domain.repositories.Repositorio;
import domain.repositories.daos.DAO;

public class RepositorioDePersonas extends Repositorio<Persona>{

    public RepositorioDePersonas(DAO<Persona> dao) {
        super(dao);
    }
}
