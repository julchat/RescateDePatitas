package domain.repositorios;

import domain.business.mascota.Chapa;
import domain.repositorios.daos.DAO;
import domain.security.Usuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioChapas extends Repositorio<Chapa> {
    public RepositorioChapas(DAO<Chapa> dao) { super(dao); }

    public Chapa buscarChapa(int id_chapa){
        return this.dao.buscar(condicionChapita(id_chapa));
    }

    private static BusquedaCondicional condicionChapita(int id_chapa){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Chapa> chapaQuery = criteriaBuilder.createQuery(Chapa.class);

        Root<Chapa> condicionRaiz = chapaQuery.from(Chapa.class);

        Predicate condicionIdChapa = criteriaBuilder.equal(condicionRaiz.get("id"), id_chapa);

        chapaQuery.where(condicionIdChapa);

        return new BusquedaCondicional(null, chapaQuery);
    }


}
