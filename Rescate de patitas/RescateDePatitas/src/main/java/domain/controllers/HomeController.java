package domain.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import domain.business.caracteristicas.Caracteristica;
import domain.business.mascota.Chapa;
import domain.business.mascota.Mascota;
import domain.business.mascota.MascotaPerdida;
import domain.business.publicaciones.Pregunta;
import domain.business.publicaciones.PublicacionMascotaEnAdopcion;
import domain.business.publicaciones.PublicacionMascotaPerdida;
import domain.business.users.Persona;
import domain.repositorios.*;
import domain.repositorios.factories.*;
import domain.security.Admin;
import domain.security.TipoRol;
import domain.security.Usuario;
import json.JsonController;
import json.JsonMap;
import org.eclipse.jetty.util.IO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeController {

    public String inicio( Request request , Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("home");

        return template.text();
    }

    public String sinPermisos(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("sin-permisos");

        return template.text();
    }

    public String iniciarSesion(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("iniciar-sesion");

        return template.text();
    }

    public String registrarUsuario(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("registrarse");

        return template.text();
    }


    public String editarPerfil(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("editar-perfil");

        return template.text();
    }

    public String mascotasRegistradas(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-registradas");

        return template.text();
    }


    public String registrarMascota(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("registrar-mascota");

        return template.text();
    }


    public String mascotaPerdidaChapita(Request request, Response response) throws IOException {

        int idChapita = new Integer(request.params("id"));
        System.out.println("ID CHAPA: " + idChapita);
        RepositorioChapas repositorioChapas = FactoryRepositorioChapas.get();
        Chapa chapita = repositorioChapas.buscarChapa(idChapita);

        if(chapita == null) {
            TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
            Handlebars handlebars = new Handlebars(loader);
            Template template = handlebars.compile("no-existe-pagina");

            return template.text();
        }
        else {
            TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
            Handlebars handlebars = new Handlebars(loader);
            Template template = handlebars.compile("reportar-mascota-chapita");

            Map<String, Object> viewModel = new HashMap<>();
            viewModel.put("chapita", repositorioChapas.buscarChapa(idChapita));

            return template.apply(viewModel);
        }
    }

    public String mascotaPerdida(Request request , Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("reportar-mascota");

        return template.text();
    }


    public String mascotasPerdidas(Request request, Response response) throws IOException {
        RepositorioPubliMascotaPerdida repositorioPubliMascotaPerdida = FactoryRepositorioPubliMascotaPerdida.get();

        List<PublicacionMascotaPerdida> mascotasPerdidas = repositorioPubliMascotaPerdida.buscarTodos();

        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-perdidas");
        Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("mascotasPerdidas", mascotasPerdidas);

        return template.apply(viewModel);
    }

    public String estoyPerdido(Request request, Response response) throws IOException {
        RepositorioPubliMascotaPerdida repositorioPubliMascotaPerdida = FactoryRepositorioPubliMascotaPerdida.get();

        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("estoy-perdido");

        int idPublicacion = new Integer(request.params("id"));
        System.out.println(idPublicacion);

        PublicacionMascotaPerdida publicacionMascotaPerdida = repositorioPubliMascotaPerdida.buscar(idPublicacion);
        System.out.println(JsonController.transformar(publicacionMascotaPerdida));

        Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("publicacion", publicacionMascotaPerdida);

        return template.apply(viewModel);
    }

    public String notificarRescatista(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("notificar-rescatista");

        int idPublicacion = new Integer(request.params("id"));
        Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("id", idPublicacion);

        return template.apply(viewModel);
    }


    public String mascotasEnAdopcion(Request request, Response response) throws IOException {
        RepositorioPubliMascotaEnAdopcion repositorioPubliMascotaEnAdopcion = FactoryRepositorioPubliMascotaEnAdopcion.get();
        List<PublicacionMascotaEnAdopcion> publicaciones = repositorioPubliMascotaEnAdopcion.buscarTodos();

        Map<String, Object> viewModel = new HashMap<>();
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-en-adopcion");

        viewModel.put("mascotasEnAdopcion", publicaciones);

        return template.apply(viewModel);
    }

    public String estoyEnAdopcion(Request request, Response response) throws IOException {
        RepositorioPubliMascotaEnAdopcion repositorioPubliMascotaEnAdopcion = FactoryRepositorioPubliMascotaEnAdopcion.get();

        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("estoy-en-adopcion");

        int idMascota = new Integer(request.params("id"));
        System.out.println(idMascota);

        PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion = repositorioPubliMascotaEnAdopcion.buscar(idMascota);
        System.out.println(JsonController.transformar(publicacionMascotaEnAdopcion));

        Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("publicacion", publicacionMascotaEnAdopcion);

        return template.apply(viewModel);
    }

    public String notificarDuenio(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("notificar-duenio");

        int idPublicacion = new Integer(request.params("id"));
        Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("id", idPublicacion);

        return template.apply(viewModel);
    }

    public String buscarMascotaIdeal(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("busqueda-mascota-ideal");

        return template.text();
    }


    // Todo: No se si esta va
    public String adoptarMascota(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("adoptar-mascota");

        return template.text();
    }


    public String darMascotaAdopcion(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("dar-mascota-adopcion");

        return template.text();
    }

    public String darMascotaAdopcionParticular(Request request, Response response) throws IOException {

        String idSesion = request.headers("Authorization");
        System.out.println("ID SESION: " + idSesion);

        if(idSesion == null) {
            response.redirect("/sin-permisos");
            return null;
        }
        else {
            int idMascota = new Integer(request.params("id"));

            RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();
            Mascota mascota = repositorioMascotas.buscar(idMascota);

            if(mascota == null) {
                TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
                Handlebars handlebars = new Handlebars(loader);
                Template template = handlebars.compile("no-existe-pagina");

                return template.text();
            }
            else {
                Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
                Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");

                RepositorioUsuarios repositorioUsuarios = FactoryRepositorioUsuarios.get();
                RepositorioPersonas repositorioPersonas = FactoryRepositorioPersonas.get();
                Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());
                Persona persona = repositorioPersonas.buscar(usuario.getPersona().getId());

                RepositorioPreguntas repositorioPreguntas = FactoryRepositorioPreguntas.get();
                List<Pregunta> preguntas = repositorioPreguntas.buscarTodos();

                TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
                Handlebars handlebars = new Handlebars(loader);
                Template template = handlebars.compile("dar-mascota-adopcion-particular");
                Map<String, Object> viewModel = new HashMap<>();

                viewModel.put("persona", persona);
                viewModel.put("mascota", mascota);
                viewModel.put("preguntas", preguntas);

                return template.apply(viewModel);
            }
        }
    }


    public String adminCaracteristicas(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("adm-caracteristicas");

        return template.text();
    }

    public String adminUsuarios(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("adm-usuarios");

        return template.text();
    }


    public String publicacionesPendientes(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("publicaciones-pendientes");

        return template.text();
    }

}
