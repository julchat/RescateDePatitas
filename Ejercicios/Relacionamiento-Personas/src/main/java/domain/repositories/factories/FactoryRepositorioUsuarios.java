package domain.repositories.factories;

import config.Config;
import domain.business.User;
import domain.repositories.RepositorioDeUsuarios;
import domain.repositories.daos.DAO;
import domain.repositories.daos.DAOHibernate;
import domain.repositories.daos.DAOMemoria;
import domain.repositories.testMemoData.Data;

public class FactoryRepositorioUsuarios {
    private static RepositorioDeUsuarios repo;

    static {
        repo = null;
    }

    public static RepositorioDeUsuarios get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<User> dao = new DAOHibernate<>(User.class);
                repo = new RepositorioDeUsuarios(dao);
            }
            else{
                repo = new RepositorioDeUsuarios(new DAOMemoria<>(Data.getData(User.class)));
            }
        }
        return repo;
    }
}