package domain.controllers;

import domain.model.Actor;
import domain.model.Persona;
import domain.model.User;
import domain.model.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.RepositorioDeUsuarios;
import domain.repositories.factories.FactoryRepositorio;
import domain.repositories.factories.FactoryRepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    public ModelAndView inicio(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"login.hbs");
    }

    public Response login(Request request, Response response){
        try{
            RepositorioDeUsuarios repoUsuarios = FactoryRepositorioUsuarios.get();

            String nombreDeUsuario = request.queryParams("nombreDeUsuario");
            String contrasenia     = request.queryParams("contrasenia");

            if(repoUsuarios.existe(nombreDeUsuario, contrasenia)){
                User usuario = repoUsuarios.buscarUsuario(nombreDeUsuario, contrasenia);

                request.session(true);
                request.session().attribute("id", usuario.getId());

                response.redirect("/home");
            }
            else{
                response.redirect("/");
            }
        }
        catch (Exception e){
            //Funcionalidad disponible solo con persistencia en Base de Datos
            response.redirect("/home");
        }
        finally {
            return response;
        }
    }

    public ModelAndView crear(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"sign-up.hbs");
    }


    public Response crearUsuario(Request request, Response response) {
        User usuario = new User();
        signup(usuario, request);
        response.redirect("/sign-up");
        return response;
    }


    public void signup(User usuario, Request request) {

        usuario.setActor(new Usuario());

        if(request.queryParams("nombre") != null){
            //Repositorio<Usuario> repoUsuarios = FactoryRepositorio.get(Usuario.class);
            //Usuario unUsuario = repoUsuarios.buscar(new Usuario());
            usuario.getActor().setNombre(request.queryParams("nombre"));
        }

        if(request.queryParams("apellido") != null){
            usuario.getActor().setApellido(request.queryParams("apellido"));
        }

        if(request.queryParams("nro_documento") != null){
            int nroDocumento = new Integer(request.queryParams("nro_documento"));
            usuario.getActor().setNroDocumento(nroDocumento);
        }

        if(request.queryParams("usuario") != null){
            usuario.setUsuario(request.queryParams("usuario"));
        }

        if(request.queryParams("password") != null){
            usuario.setPassword(request.queryParams("password"));
        }

        if(request.queryParams("confirmar_password") != null){
            if(request.queryParams("confirmar_password") != usuario.getPassword()) {
                // Todo: tirar un Modal que diga que la contraseña no coincide
            }
        }
    }

    public Response logout(Request request, Response response){
        request.session().invalidate();
        response.redirect("/");
        return response;
    }
}