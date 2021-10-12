package domain.controllers;

import domain.repositorios.RepositorioUsuarios;
import domain.repositorios.factories.FactoryRepositorioUsuarios;
import domain.security.Usuario;
import domain.security.password.PasswordStatus;
import domain.security.password.ValidadorPassword;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

            if(repoUsuarios.existe(nombreUsuario)){

                String password = request.queryParams("password");
                response.cookie("usuario_login", nombreUsuario);
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


    public Response registrarse(Request request, Response response) {
        try {
            RepositorioUsuarios repoUsuarios = FactoryRepositorioUsuarios.get();

            String nombreUsuario = request.queryParams("user");
            if(repoUsuarios.existe(nombreUsuario)) {
                // TODO: tirar un Modal, error, o lo que sea avisando que ese usuario esta en uso
                response.redirect("/sign-up");
            }
            else {
                Usuario nuevoUsuario = new Usuario();
                String contrasenia = request.queryParams("password");
                nuevoUsuario.setNombreUsuario(nombreUsuario);
                nuevoUsuario.setContrasenia(contrasenia);
                ValidadorPassword validador = new ValidadorPassword();
                if(validador.esValida(nombreUsuario, contrasenia)) {
                    repoUsuarios.agregar(nuevoUsuario);
                    // Todo: tirar un cartel diciendo que el usuario se agrego correctamente
                    response.redirect("/sign-in");
                }
                else {
                    PasswordStatus passwordStatus = PasswordStatus.getInstance();
                    // Todo: tirar mensajes de acuerdo a los errores que comete dicha contraseña
                    List<String> lista = validador.verificarPassword(nombreUsuario, contrasenia);
                    lista.stream().filter(s -> !s.equals(passwordStatus.getStatusOK()));

                    for(String string : lista) {
                        System.out.println(string);
                    }

                }

            }
        }
        catch (Exception e) {
            //Todo: aca podria tirar una excepcion de que la contraseña no es valida, o insegura, or whatever
        }
        finally {
            return response;
        }

    }



}