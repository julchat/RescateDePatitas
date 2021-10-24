package domain.repositorios.factories;

import config.Config;
import domain.business.mascota.MascotaPerdida;
import domain.business.users.Rescatista;
import domain.repositorios.RepositorioRescatista;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;

public class FactoryRepositorioRescatista {
    private static RepositorioRescatista repo;

    static {
        repo = null;
    }

    public static RepositorioRescatista get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Rescatista> dao = new DAOHibernate<>(Rescatista.class);
                repo = new RepositorioRescatista(dao);
            }
            else{
                // repo = new RepositorioUsuarios(new DAOMemoria<>(Data.getData(Usuario.class)));
                repo = new RepositorioRescatista(null);
            }
        }
        return repo;
    }
}
