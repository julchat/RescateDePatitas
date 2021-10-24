package domain.repositorios;

import domain.business.users.Duenio;
import domain.business.users.Persona;
import domain.repositorios.Repositorio;
import domain.repositorios.daos.DAO;

public class RepositorioPersonas extends Repositorio<Persona> {

    public RepositorioPersonas(DAO<Persona> dao) { super(dao); }

    public Duenio buscarDuenio(int idDuenio) {
        return (Duenio)this.dao.buscar(idDuenio);
    }
}
