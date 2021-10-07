package domain.repositorios.factories;

import config.Config;
import domain.repositorios.RepositorioDeUsuarios;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;
import domain.repositorios.daos.DAOMemoria;
import domain.security.Usuario;


import javax.xml.crypto.Data;

public class FactoryRepositorioUsuarios {
    private static RepositorioDeUsuarios repo;

    static {
        repo = null;
    }

    public static RepositorioDeUsuarios get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
                repo = new RepositorioDeUsuarios(dao);
            }
            else{
               // repo = new RepositorioDeUsuarios(new DAOMemoria<>(Data.getData(Usuario.class)));
                repo = new RepositorioDeUsuarios(null);
            }
        }
        return repo;
    }
}