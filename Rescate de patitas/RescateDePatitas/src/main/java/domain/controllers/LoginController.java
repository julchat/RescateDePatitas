package domain.controllers;

import domain.repositorios.RepositorioUsuarios;
import domain.repositorios.factories.FactoryRepositorioUsuarios;
import domain.security.Usuario;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    public ModelAndView showLogin(Request request, Response response){
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"login.hbs");
    }

    public ModelAndView showRegister(Request request, Response response){
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"register.hbs");
    }

    public ModelAndView logout(Request request, Response response){
        request.session().invalidate();
        response.redirect("/");
        return null;
    }

    public ModelAndView login (Request request, Response response) {
        EntityManager em = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaccion = em.getTransaction();
        transaccion.begin();
        String nombreUsuario = request.queryParams("usuario");
        String password = request.queryParams("password");
        response.cookie("usuario_login", nombreUsuario);

        RepositorioUsuarios repoUsuarios = FactoryRepositorioUsuarios.get();

        try {
            if(repoUsuarios.existe(nombreUsuario, password)){
                Usuario usuario = repoUsuarios.buscarUsuario(nombreUsuario, password);

                request.session(true);
                request.session().attribute("id", usuario.getId());

                response.redirect("/");
            }
            else{
                transaccion.commit();
                response.redirect("/sign-in");
            }

            return null;
        } catch(Exception exception) {
            transaccion.commit();
            response.redirect("/");
        }

        return null;
    }


    /*public ModelAndView registrarse(Request request, Response response) {

    }*/

}