package domain.repositorios.factories;

import config.Config;
import domain.business.users.Persona;
import domain.repositorios.RepositorioPersonas;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;

public class FactoryRepositorioPersonas {
    private static RepositorioPersonas repo;

    static {
        repo = null;
    }

    public static RepositorioPersonas get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Persona> dao = new DAOHibernate<>(Persona.class);
                repo = new RepositorioPersonas(dao);
            }
            else{
                repo = new RepositorioPersonas(null);
            }
        }
        return repo;
    }
}