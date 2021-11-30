package domain.repositorios.factories;

import config.Config;
import domain.business.caracteristicas.Caracteristica;
import domain.business.mascota.Chapa;
import domain.repositorios.RepositorioCaracteristicas;
import domain.repositorios.RepositorioChapas;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;

public class FactoryRepositorioCaracteristicas {
    private static RepositorioCaracteristicas repo;

    static {
        repo = null;
    }

    public static RepositorioCaracteristicas get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Caracteristica> dao = new DAOHibernate<>(Caracteristica.class);
                repo = new RepositorioCaracteristicas(dao);
            }
            else{
                repo = new RepositorioCaracteristicas(null);
            }
        }
        return repo;
    }
}
