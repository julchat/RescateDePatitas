package domain.repositorios;

import domain.business.ubicacion.Domicilio;
import domain.repositorios.daos.DAO;

public class RepositorioDomicilios extends Repositorio<Domicilio>{
    public RepositorioDomicilios(DAO<Domicilio> dao) { super(dao); }
}
