package domain.repositorios.factories;

import config.Config;
import domain.business.mascota.Chapa;
import domain.repositorios.RepositorioChapas;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;

public class FactoryRepositorioChapas {
    private static RepositorioChapas repo;

    static {
        repo = null;
    }

    public static RepositorioChapas get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Chapa> dao = new DAOHibernate<>(Chapa.class);
                repo = new RepositorioChapas(dao);
            }
            else{
                // repo = new RepositorioUsuarios(new DAOMemoria<>(Data.getData(Usuario.class)));
                repo = new RepositorioChapas(null);
            }
        }
        return repo;
    }
}
