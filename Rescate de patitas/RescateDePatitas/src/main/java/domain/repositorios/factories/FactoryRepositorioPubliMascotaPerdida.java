package domain.repositorios.factories;

import config.Config;
import domain.business.publicaciones.Publicacion;
import domain.business.publicaciones.PublicacionMascotaPerdida;
import domain.repositorios.RepositorioPubliMascotaPerdida;
import domain.repositorios.RepositorioPublicaciones;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;

public class FactoryRepositorioPubliMascotaPerdida {
    private static RepositorioPubliMascotaPerdida repo;

    static {
        repo = null;
    }

    public static RepositorioPubliMascotaPerdida get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<PublicacionMascotaPerdida> dao = new DAOHibernate<>(PublicacionMascotaPerdida.class);
                repo = new RepositorioPubliMascotaPerdida(dao);
            }
            else{
                repo = new RepositorioPubliMascotaPerdida(null);
            }
        }
        return repo;
    }
}
