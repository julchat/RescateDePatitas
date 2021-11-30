package domain.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.gson.Gson;
import domain.repositorios.RepositorioUsuarios;
import domain.repositorios.factories.FactoryRepositorioUsuarios;
import domain.security.Usuario;
import json.JsonMap;
import json.Mensaje;
import json.Sesion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private RepositorioUsuarios repositorio = FactoryRepositorioUsuarios.get();


    public String iniciarSesion(Request request, Response response) {

        Sesion nuevaSesion = new Gson().fromJson(request.body(), Sesion.class);

        String nombreUsuario = nuevaSesion.getUserName();
        String password = nuevaSesion.getUserPassword();

        try {
            Usuario usuario = repositorio.buscarUsuario(nombreUsuario);
            if (usuario != null && usuario.validarLogin(nombreUsuario, password)) {

                System.out.println("Login: " + usuario.getNombreUsuario());
                System.out.println("Rol: " + usuario.getTipoRol());

                SesionManager sesionManager = SesionManager.get();
                String idSesion = sesionManager.crear("usuario", usuario);

                System.out.println("ID Sesion: " + idSesion);
                response.status(200);
                return new LoginResponse(idSesion).transformar();
            }
            else {
                System.out.println("La contraseña es incorrecta.");
                response.status(400);
                return new Mensaje("Contraseña incorrecta").transformar();
            }
        }
        catch (Exception e) {
            System.out.println("El usuario no existe");
            response.status(404);
            return new Mensaje("El usuario no existe.").transformar();
        }
    }

    public String logout(Request request, Response response){
        String idSesion = request.headers("Authorization");
        SesionManager.get().eliminar(idSesion);
        System.out.println("Cerrando Sesion de " + idSesion);
        return new Mensaje("Cerrando Sesion...").transformar();
    }
}