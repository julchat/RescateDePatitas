package domain.repositorios.factories;

import config.Config;
import domain.business.mascota.MascotaPerdida;
import domain.business.users.Persona;
import domain.repositorios.RepositorioMascotaPerdida;
import domain.repositorios.RepositorioPersonas;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;

public class FactoryRepositorioMascotaPerdida {
    private static RepositorioMascotaPerdida repo;

    static {
        repo = null;
    }

    public static RepositorioMascotaPerdida get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<MascotaPerdida> dao = new DAOHibernate<>(MascotaPerdida.class);
                repo = new RepositorioMascotaPerdida(dao);
            }
            else{
                // repo = new RepositorioUsuarios(new DAOMemoria<>(Data.getData(Usuario.class)));
                repo = new RepositorioMascotaPerdida(null);
            }
        }
        return repo;
    }
}
