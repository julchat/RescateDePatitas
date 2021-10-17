package domain.repositorios;

import domain.repositorios.daos.DAO;
import domain.security.Usuario;
import domain.security.password.AESEncryptionDecryption;
import domain.security.password.ValidadorPassword;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioUsuarios extends Repositorio<Usuario> {

    public RepositorioUsuarios(DAO<Usuario> dao) { super(dao); }

    public boolean existe(String nombreDeUsuario){
        return this.dao.buscar(existeUsuario(nombreDeUsuario)) != null;
    }

    /*public Usuario buscarUsuario(String nombreDeUsuario, String contrasenia){
        return this.dao.buscar(condicionUsuarioYContrasenia(nombreDeUsuario, contrasenia));
    }*/

    public Usuario buscarUsuario(String nombreDeUsuario){
        return this.dao.buscar(existeUsuario(nombreDeUsuario));
    }

    private BusquedaCondicional existeUsuario(String nombreDeUsuario) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

        Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("nombreUsuario"), nombreDeUsuario);
        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario);
        usuarioQuery.where(condicionExisteUsuario);

        return new BusquedaCondicional(null, usuarioQuery);
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

    public void guardarUsuario(Usuario nuevoUsuario, String password) {
        String passwordEncriptada = AESEncryptionDecryption.encrypt(password);
        nuevoUsuario.setContrasenia(passwordEncriptada);
        this.agregar(nuevoUsuario);
    }
}