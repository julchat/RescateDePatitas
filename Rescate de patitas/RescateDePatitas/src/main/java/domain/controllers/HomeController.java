package domain.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import domain.business.caracteristicas.Caracteristica;
import domain.business.mascota.Chapa;
import domain.business.mascota.Mascota;
import domain.business.mascota.MascotaPerdida;
import domain.business.publicaciones.*;
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
import java.util.stream.Collectors;

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

    public String noExiste(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("no-existe-pagina");

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
            response.redirect("/no-existe");
            return null;
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

        List<PublicacionMascotaPerdida> publicaciones = repositorioPubliMascotaPerdida.buscarTodos();
        List<PublicacionMascotaPerdida> publicacionesAceptadas = publicaciones.stream().filter(publicacion -> publicacion.getEstado().equals(Estados.APROBADA)).collect(Collectors.toList());

        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-perdidas");
        Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("mascotasPerdidas", publicacionesAceptadas);

        return template.apply(viewModel);
    }

    public String mascotasPerdidasPesado(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-perdidas-pesado");

        return template.text();
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
        List<PublicacionMascotaEnAdopcion> publicacionesAceptadas = publicaciones.stream().filter(publicacion -> publicacion.getEstado().equals(Estados.APROBADA)).collect(Collectors.toList());

        Map<String, Object> viewModel = new HashMap<>();
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-en-adopcion");

        viewModel.put("mascotasEnAdopcion", publicacionesAceptadas);

        return template.apply(viewModel);
    }


    public String mascotasEnAdopcionPesado(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-en-adopcion-pesado");

        return template.text();
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


    public String darMascotaAdopcion(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("dar-mascota-adopcion");

        return template.text();
    }

    public String darMascotaAdopcionParticular(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("dar-mascota-adopcion-particular");

        return template.text();
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
