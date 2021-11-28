package domain.repositorios.factories;

import config.Config;
import domain.business.users.Contacto;
import domain.repositorios.RepositorioContactos;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;

public class FactoryRepositorioContacto {
    private static RepositorioContactos repo;

    static {
        repo = null;
    }

    public static RepositorioContactos get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Contacto> dao = new DAOHibernate<>(Contacto.class);
                repo = new RepositorioContactos(dao);
            }
            else{
                repo = new RepositorioContactos(null);
            }
        }
        return repo;
    }
}