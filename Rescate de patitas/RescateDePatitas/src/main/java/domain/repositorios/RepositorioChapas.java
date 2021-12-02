package domain.repositorios;

import domain.business.mascota.Chapa;
import domain.business.mascota.Mascota;
import domain.business.users.Persona;
import domain.repositorios.daos.DAO;
import domain.security.Usuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioChapas extends Repositorio<Chapa> {
    public RepositorioChapas(DAO<Chapa> dao) { super(dao); }

    public Chapa buscarChapa(int idChapa){
        return this.dao.buscar(idChapa);
    }

    public Persona buscarDuenio(int idMascota) {
        Chapa chapa = this.dao.buscar(obtenerChapa(idMascota));
        return chapa.getDuenio();
    }

    private BusquedaCondicional obtenerChapa(int idMascota) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Chapa> chapaQuery = criteriaBuilder.createQuery(Chapa.class);
        Root<Chapa> condicionRaiz = chapaQuery.from(Chapa.class);

        Predicate condicionMascota = criteriaBuilder.equal(condicionRaiz.get("mascota"), idMascota);
        Predicate condicionExisteMascota = criteriaBuilder.and(condicionMascota);
        chapaQuery.where(condicionExisteMascota);

        return new BusquedaCondicional(null, chapaQuery);
    }

}
