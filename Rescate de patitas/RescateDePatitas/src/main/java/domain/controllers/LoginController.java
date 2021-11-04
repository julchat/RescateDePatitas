package domain.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import domain.repositorios.RepositorioUsuarios;
import domain.repositorios.factories.FactoryRepositorioUsuarios;
import domain.security.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private RepositorioUsuarios repositorio = FactoryRepositorioUsuarios.get();

    public String iniciarSesion(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("iniciar-sesion");

        return template.text();
    }

    public Response iniciarSesionPost(Request request, Response response) {

        String nombreUsuario = request.queryParams("userName");
        String password = request.queryParams("userPassword");

        try {
            Usuario usuario = repositorio.buscarUsuario(nombreUsuario);
            if (usuario != null && usuario.validarLogin(nombreUsuario, password)) {

                response.cookie("user", nombreUsuario);
                request.session(true);
                request.session().attribute("id", usuario.getId());

                response.redirect("/home");
            }

            else {
                // Todo: tirar que la contraseña es incorrecta
                System.out.println("La contraseña es incorrecta.");
                response.redirect("/iniciar-sesion");
            }
        }
        catch (Exception e) {
            // TODO: tirar un mensaje que el usuario no existe
            System.out.println("El usuario no existe");
            response.redirect("/iniciar-sesion");
        }
        finally {
            return response;
        }
    }

    public ModelAndView logout(Request request, Response response){
        request.session().removeAttribute("user");
        response.redirect("/");
        return null;
    }
}