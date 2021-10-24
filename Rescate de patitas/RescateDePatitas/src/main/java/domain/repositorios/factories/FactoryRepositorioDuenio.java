package domain.repositorios.factories;

import config.Config;
import domain.business.users.Duenio;
import domain.repositorios.RepositorioDuenio;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;

public class FactoryRepositorioDuenio {
    private static RepositorioDuenio repo;

    static {
        repo = null;
    }

    public static RepositorioDuenio get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Duenio> dao = new DAOHibernate<>(Duenio.class);
                repo = new RepositorioDuenio(dao);
            }
            else{
                // repo = new RepositorioUsuarios(new DAOMemoria<>(Data.getData(Usuario.class)));
                repo = new RepositorioDuenio(null);
            }
        }
        return repo;
    }
}
