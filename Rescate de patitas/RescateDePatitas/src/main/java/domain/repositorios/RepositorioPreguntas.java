package domain.repositorios;

import domain.business.publicaciones.Pregunta;
import domain.repositorios.daos.DAO;

public class RepositorioPreguntas extends Repositorio<Pregunta>{
    public RepositorioPreguntas(DAO<Pregunta> dao) { super(dao); }
}
