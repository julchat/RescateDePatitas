package domain.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.gson.Gson;
import domain.business.Sistema;
import domain.business.publicaciones.Estados;
import domain.business.publicaciones.Publicacion;
import domain.business.publicaciones.PublicacionMascotaPerdida;
import domain.repositorios.*;
import domain.repositorios.factories.*;
import domain.security.Usuario;
import json.FormEstadoPublicacion;
import json.JsonController;
import json.Mensaje;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PublicacionesController {
    private RepositorioUsuarios repositorioUsuarios = FactoryRepositorioUsuarios.get();
    //private RepositorioPersonas repositorioPersonas = FactoryRepositorioPersonas.get();

    private RepositorioPublicaciones repositorioPublicaciones = FactoryRepositorioPublicaciones.get();
    private RepositorioPubliMascotaPerdida repositorioPubliMascotaPerdida = FactoryRepositorioPubliMascotaPerdida.get();
    private RepositorioPubliMascotaEnAdopcion repositorioPubliMascotaEnAdopcion = FactoryRepositorioPubliMascotaEnAdopcion.get();

    public String publicacion(Request request , Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("publicacion");

        return template.text();
    }

    public String administrarPublicacion(Request request, Response response) {

        return null;
    }

    /* ==============================================================================================================
    Administrar Publicaciones -> El Voluntario o Moderador decide si aprueba o rechaza las publicaciones pendientes
   ============================================================================================================== */

    public String aprobarPublicacion(Request request, Response response) {
        Sistema miSistema = Sistema.getInstance();

        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        try {
            Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
            Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
            System.out.println("Login: " + sesionUsuario.getNombreUsuario());

            Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());

            if(miSistema.validarRol(usuario.getTipoRol()).puedoAprobarPublicaciones()) {
                System.out.println("Validando permisos...");

                FormEstadoPublicacion formPublicacion = new Gson().fromJson(request.body(), FormEstadoPublicacion.class);
                System.out.println(request.body());

                if(formPublicacion.getPublicacionID() != "" || formPublicacion.getPublicacionID() != null) {
                    int idPublicacion = new Integer(formPublicacion.getPublicacionID());
                    System.out.println(idPublicacion);

                    Publicacion publicacionObtenida = repositorioPublicaciones.buscar(idPublicacion);

                    publicacionObtenida.setEstado(Estados.APROBADA);
                    repositorioPublicaciones.modificar(publicacionObtenida);

                    response.status(200);
                    return new Mensaje("Se aprobó la publicación.").transformar();
                }
                else {
                    System.out.println("No se eligió ninguna publicación.");
                    response.status(204);
                    return new Mensaje("No se eligió ninguna pubicación.").transformar();
                }
            }
            else {
                System.out.println("No tiene permisos suficientes.");
                response.status(203);
                return new Mensaje("No hay permisos suficientes.").transformar();
            }
        }
        catch (NullPointerException e) {
            System.out.println("No tiene permisos suficientes.");
            response.status(203);
            return new Mensaje("No hay permisos suficientes.").transformar();
        }

    }

    public String rechazarPublicacion(Request request, Response response) {
        Sistema miSistema = Sistema.getInstance();

        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        try {
            Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
            Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
            System.out.println("Login: " + sesionUsuario.getNombreUsuario());

            Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());

            if(miSistema.validarRol(usuario.getTipoRol()).puedoAprobarPublicaciones()) {
                System.out.println("Validando permisos...");

                FormEstadoPublicacion formPublicacion = new Gson().fromJson(request.body(), FormEstadoPublicacion.class);
                System.out.println(request.body());

                if(formPublicacion.getPublicacionID() != "" || formPublicacion.getPublicacionID() != null) {
                    int idPublicacion = new Integer(formPublicacion.getPublicacionID());
                    System.out.println(idPublicacion);

                    Publicacion publicacionObtenida = repositorioPublicaciones.buscar(idPublicacion);

                    publicacionObtenida.setEstado(Estados.RECHAZADA);
                    repositorioPublicaciones.modificar(publicacionObtenida);

                    response.status(200);
                    return new Mensaje("Se rechazó la publicación.").transformar();
                }
                else {
                    System.out.println("No se eligió ninguna publicación.");
                    response.status(204);
                    return new Mensaje("No se eligió ninguna pubicación.").transformar();
                }

            }
            else {
                System.out.println("No tiene permisos suficientes.");
                response.status(203);
                return new Mensaje("No hay permisos suficientes.").transformar();
            }
        }
        catch (NullPointerException e) {
            System.out.println("No tiene permisos suficientes.");
            response.status(203);
            return new Mensaje("No hay permisos suficientes.").transformar();
        }

    }
}
