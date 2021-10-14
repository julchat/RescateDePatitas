package domain.repositorios;

import domain.business.organizaciones.HogarDeTransito;
import domain.repositorios.daos.DAO;

public class RepositorioHogares extends Repositorio<HogarDeTransito> {

    public RepositorioHogares(DAO<HogarDeTransito> dao) { super(dao); }
}
