package domain.repositorios.factories;

import config.Config;
import domain.business.publicaciones.Publicacion;
import domain.business.users.Persona;
import domain.repositorios.RepositorioPersonas;
import domain.repositorios.RepositorioPublicaciones;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;

public class FactoryRepositorioPublicaciones {
    private static RepositorioPublicaciones repo;

    static {
        repo = null;
    }

    public static RepositorioPublicaciones get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Publicacion> dao = new DAOHibernate<>(Publicacion.class);
                repo = new RepositorioPublicaciones(dao);
            }
            else{
                // repo = new RepositorioUsuarios(new DAOMemoria<>(Data.getData(Usuario.class)));
                repo = new RepositorioPublicaciones(null);
            }
        }
        return repo;
    }
}
