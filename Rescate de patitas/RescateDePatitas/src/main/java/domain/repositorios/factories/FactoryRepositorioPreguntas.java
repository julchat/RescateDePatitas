package domain.repositorios.factories;

import config.Config;
import domain.business.publicaciones.Pregunta;
import domain.repositorios.RepositorioPreguntas;
import domain.repositorios.daos.DAO;
import domain.repositorios.daos.DAOHibernate;

public class FactoryRepositorioPreguntas {
    private static RepositorioPreguntas repo;

    static {
        repo = null;
    }

    public static RepositorioPreguntas get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Pregunta> dao = new DAOHibernate<>(Pregunta.class);
                repo = new RepositorioPreguntas(dao);
            }
            else{
                repo = new RepositorioPreguntas(null);
            }
        }
        return repo;
    }
}
