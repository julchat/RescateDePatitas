package domain.repositorios;

import domain.business.publicaciones.PublicacionMascotaEnAdopcion;
import domain.repositorios.daos.DAO;

public class RepositorioPubliMascotaEnAdopcion extends Repositorio<PublicacionMascotaEnAdopcion>{
    public RepositorioPubliMascotaEnAdopcion(DAO<PublicacionMascotaEnAdopcion> dao) { super(dao); }
}
