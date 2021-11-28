package domain.repositorios.factories;

import config.Config;
import domain.business.ubicacion.Domicilio;
import domain.repositorios.RepositorioDomicilios;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;

public class FactoryRepositorioDomicilios {
    private static RepositorioDomicilios repo;

    static {
        repo = null;
    }

    public static RepositorioDomicilios get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Domicilio> dao = new DAOHibernate<>(Domicilio.class);
                repo = new RepositorioDomicilios(dao);
            }
            else{
                // repo = new RepositorioUsuarios(new DAOMemoria<>(Data.getData(Usuario.class)));
                repo = new RepositorioDomicilios(null);
            }
        }
        return repo;
    }
}
