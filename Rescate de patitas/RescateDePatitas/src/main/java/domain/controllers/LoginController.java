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
        return new ModelAndView(viewModel,"sign-up.hbs");
    }

    public ModelAndView logout(Request request, Response response){
        request.session().invalidate();
        response.redirect("/");
        return null;
    }

    public Response login (Request request, Response response) {

        try {
            RepositorioUsuarios repoUsuarios = FactoryRepositorioUsuarios.get();

            String nombreUsuario = request.queryParams("user");
            String password = request.queryParams("password");
            response.cookie("usuario_login", nombreUsuario);

            if(repoUsuarios.existe(nombreUsuario, password)){
                Usuario usuario = repoUsuarios.buscarUsuario(nombreUsuario, password);
                request.session(true);
                request.session().attribute("id", usuario.getId());
                response.redirect("/home2");
            }
            else{
                // TODO: tirar un mensaje que el usuario no existe
                System.out.println("El usuario no existe");
                response.redirect("/sign-in");
            }
        }
        catch(Exception exception) {
            System.out.println("Hay un error en la busqueda");
            // Todo: deberia tirar un modal, marcando que hay un error y no existe el usuario o la contraseña es incorrecta
            response.redirect("/sign-in");
        }
        finally {
            return response;
        }
    }


    /*public ModelAndView registrarse(Request request, Response response) {

    }*/

}