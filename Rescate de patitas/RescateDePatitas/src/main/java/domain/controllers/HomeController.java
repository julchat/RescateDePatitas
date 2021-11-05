package domain.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import domain.business.mascota.MascotaPerdida;
import domain.business.users.Duenio;
import domain.repositorios.RepositorioMascotaPerdida;
import domain.repositorios.factories.FactoryRepositorioMascotaPerdida;
import domain.security.Admin;
import domain.security.TipoRol;
import domain.security.Usuario;
import json.JsonMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeController {

    public String inicio( Request request , Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("inicio");

        return template.text();
    }

    public String homeSesion( Request request , Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("home");

        return template.text();
    }

    public String showEditarPerfil(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("editar-perfil");

        System.out.println("Recibido: " + request.body());

        return template.text();
    }

    public String registrarse(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("registrarse");

        return template.text();
    }

    public String mascotasEnAdopcion(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-en-adopcion");

        return template.text();
    }

    public String adoptarMascota(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("adoptar-mascota");

        return template.text();
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

    public String registrarMascotaPerdida(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("registrar-mascota");

        return template.text();
    }

    public String reportarMascotaPerdida(Request request , Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("reportar-mascota");

        return template.text();
    }


    public String notificarDuenio(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("notificar-duenio");

        return template.text();
    }

    public String notificarRescatista(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("notificar-rescatista");

        return template.text();
    }


    public String mascotasPerdidas(Request request, Response response) throws IOException {
        //RepositorioMascotaPerdida repositorioMascotaPerdida = FactoryRepositorioMascotaPerdida.get();

        //List<MascotaPerdida> mascotasPerdidas = repositorioMascotaPerdida.buscarTodos();

        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-perdidas");
        //viewModel.put("mascotasPerdidas", mascotasPerdidas);
        return template.text();
    }
/*
    public List<String> mascotasPerdidas(Request request, Response response) {
        RepositorioMascotaPerdida repositorioMascotaPerdida = FactoryRepositorioMascotaPerdida.get();


        response.type("application/json");
        List<MascotaPerdida> mascotasPerdidas = repositorioMascotaPerdida.buscarTodos();

        List<String> listaJson = new ArrayList<>();
        for(MascotaPerdida mascota : mascotasPerdidas) {
            mascota.mostrarMascota();
            listaJson.add(new JsonMap<MascotaPerdida>(mascota, "mascota").transformar());
        }

        return listaJson;
        //return new JsonMap<MascotaPerdida>(null, "Mensaje").transformar();
    }*/

}
