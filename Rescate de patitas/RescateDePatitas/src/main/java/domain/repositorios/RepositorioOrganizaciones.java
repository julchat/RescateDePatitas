package domain.repositorios;

import domain.business.organizaciones.Organizacion;
import domain.repositorios.daos.DAO;

public class RepositorioOrganizaciones extends Repositorio<Organizacion>{

    public RepositorioOrganizaciones(DAO<Organizacion> dao) { super(dao); }
}
