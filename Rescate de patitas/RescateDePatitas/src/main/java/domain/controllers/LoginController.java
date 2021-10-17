package domain.controllers;

import domain.repositorios.RepositorioUsuarios;
import domain.repositorios.factories.FactoryRepositorioUsuarios;
import domain.security.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginController {
    private RepositorioUsuarios repositorio = FactoryRepositorioUsuarios.get();

    public ModelAndView showLogin(Request request, Response response){
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"login.hbs");
    }

    public ModelAndView logout(Request request, Response response){
        request.session().removeAttribute("usuarioLogin");
        response.redirect("/");
        return null;
    }

    public Response login(Request request, Response response) {

        String nombreUsuario = request.queryParams("userName");
        String password = request.queryParams("userPassword");

        try {
            Usuario usuario = repositorio.buscarUsuario(nombreUsuario);
            if (usuario != null && usuario.validarLogin(nombreUsuario, password)) {
                System.out.println("Existe el usuario y ahora valido si puedo entrar");

                response.cookie("usuarioLogin", nombreUsuario);
                request.session(true);
                request.session().attribute("id", usuario.getId());

                // Todo: verificar que tipo de rol tiene y redirigir a la pantalla correspondiente?

                response.redirect("/home2");
            }
            else {
                // Todo: tirar que la contraseña es incorrecta
                System.out.println("La contraseña es incorrecta.");
                response.redirect("/sign-in");
            }
        }
        catch (Exception e) {
            // TODO: tirar un mensaje que el usuario no existe
            System.out.println("El usuario no existe");
            response.redirect("/sign-in");
        }
        finally {
            return response;
        }
    }
}