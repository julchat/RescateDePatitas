package domain.repositorios.factories;

import config.Config;
import domain.business.publicaciones.PublicacionMascotaEnAdopcion;
import domain.business.publicaciones.PublicacionMascotaPerdida;
import domain.repositorios.RepositorioPubliMascotaEnAdopcion;
import domain.repositorios.RepositorioPubliMascotaPerdida;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;
public class FactoryRepositorioPubliMascotaEnAdopcion {
    private static RepositorioPubliMascotaEnAdopcion repo;

    static {
        repo = null;
    }

    public static RepositorioPubliMascotaEnAdopcion get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<PublicacionMascotaEnAdopcion> dao = new DAOHibernate<>(PublicacionMascotaEnAdopcion.class);
                repo = new RepositorioPubliMascotaEnAdopcion(dao);
            }
            else{
                repo = new RepositorioPubliMascotaEnAdopcion(null);
            }
        }
        return repo;
    }
}
