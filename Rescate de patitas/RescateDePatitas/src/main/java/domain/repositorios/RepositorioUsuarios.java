package domain.repositorios;

import domain.repositorios.daos.DAO;
import domain.security.Usuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioUsuarios extends Repositorio<Usuario> {

    public RepositorioUsuarios(DAO<Usuario> dao) { super(dao); }

    public Boolean existe(String nombreDeUsuario, String contrasenia){
        return buscarUsuario(nombreDeUsuario, contrasenia) != null;
    }

    public Usuario buscarUsuario(String nombreDeUsuario, String contrasenia){
        return this.dao.buscar(condicionUsuarioYContrasenia(nombreDeUsuario, contrasenia));
    }

    private BusquedaCondicional condicionUsuarioYContrasenia(String nombreDeUsuario, String contrasenia){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

        Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("nombreUsuario"), nombreDeUsuario);
        Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("contrasenia"), contrasenia);

        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

        usuarioQuery.where(condicionExisteUsuario);

        return new BusquedaCondicional(null, usuarioQuery);
    }
}