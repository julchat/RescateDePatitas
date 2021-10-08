package domain.repositorios.factories;

import config.Config;
import domain.repositorios.RepositorioUsuarios;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;
import domain.security.Usuario;

public class FactoryRepositorioUsuarios {
    private static RepositorioUsuarios repo;

    static {
        repo = null;
    }

    public static RepositorioUsuarios get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
                repo = new RepositorioUsuarios(dao);
            }
            else{
               // repo = new RepositorioUsuarios(new DAOMemoria<>(Data.getData(Usuario.class)));
                repo = new RepositorioUsuarios(null);
            }
        }
        return repo;
    }
}