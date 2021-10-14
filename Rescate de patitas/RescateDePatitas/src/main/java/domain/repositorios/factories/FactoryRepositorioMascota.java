package domain.repositorios.factories;

import config.Config;
import domain.business.mascota.Mascota;
import domain.repositorios.RepositorioMascotas;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;

public class FactoryRepositorioMascota {
    private static RepositorioMascotas repo;

    static {
        repo = null;
    }

    public static RepositorioMascotas get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Mascota> dao = new DAOHibernate<>(Mascota.class);
                repo = new RepositorioMascotas(dao);
            }
            else{
                repo = new RepositorioMascotas(null);
            }
        }
        return repo;
    }
}

