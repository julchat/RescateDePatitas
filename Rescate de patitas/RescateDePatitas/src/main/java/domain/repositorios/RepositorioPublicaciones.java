package domain.repositorios;

import domain.business.publicaciones.Publicacion;
import domain.repositorios.daos.DAO;

public class RepositorioPublicaciones extends Repositorio<Publicacion> {

    public RepositorioPublicaciones(DAO<Publicacion> dao) { super(dao); }
}
