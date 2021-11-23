package domain.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import domain.business.mascota.Mascota;
import domain.business.mascota.MascotaPerdida;
import domain.business.users.Duenio;
import domain.repositorios.RepositorioMascotaPerdida;
import domain.repositorios.RepositorioMascotas;
import domain.repositorios.factories.FactoryRepositorioMascota;
import domain.repositorios.factories.FactoryRepositorioMascotaPerdida;
import domain.security.Admin;
import domain.security.TipoRol;
import domain.security.Usuario;
import json.JsonMap;
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

        return template.text();
    }

    public ModelAndView mascotasRegistradas(Request request, Response response) throws IOException {
        //TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        //Handlebars handlebars = new Handlebars(loader);
        //Template template = handlebars.compile("mascotas-registradas");

        //System.out.println("OBTENIENDO EL USUARIO ----------------------------");
        //String idSesion = request.headers("Authorization");
        //System.out.println("ID Sesion: " + idSesion);

        //Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
        //Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
        //System.out.println("Login: " + sesionUsuario);

        Map<String, Object> model = new HashMap<>();
        //model.put("usuario", sesionUsuario);

        //return template.apply(model);
        return new ModelAndView(model, "mascotas-registradas.hbs");
    }

    public String registrarse(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("registrarse");

        return template.text();
    }

    /*
    public String mascotasEnAdopcion(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-en-adopcion");

        return template.text();
    }*/

    public String adminCaracteristicas(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("adm-caracteristicas");



        return template.text();
    }

    public String mascotasEnAdopcion(Request request, Response response) throws IOException {
        RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();
        List<Mascota> mascotasEnAdopcion = repositorioMascotas.buscarTodos();

        Map<String, Object> viewModel = new HashMap<>();
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-en-adopcion");

        viewModel.put("mascotasEnAdopcion", mascotasEnAdopcion);

        return template.apply(viewModel);
    }


    public String adoptarMascota(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("adoptar-mascota");

        return template.text();
    }

    public String mascotaEnAdopcion(Request request, Response response) throws IOException {
        RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();

        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("estoy-en-adopcion");

        int idMascota = new Integer(request.params("id"));
        System.out.println(idMascota);
// TODO: no son mascotas en si, son publicaciones, ya que contienen MAS datos
//   contienen: Datos de la Mascota, Autor
        Mascota mascotaEnAdopcion = repositorioMascotas.buscar(idMascota);
        System.out.println(new JsonMap<Mascota>(mascotaEnAdopcion).transformar());

        Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("mascota", mascotaEnAdopcion);

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

    public String registrarMascota(Request request, Response response) throws IOException {
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

        int idPublicacion = new Integer(request.params("id"));
        Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("id", idPublicacion);

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

/*
    public String mascotasPerdidas(Request request, Response response) throws IOException {
        //RepositorioMascotaPerdida repositorioMascotaPerdida = FactoryRepositorioMascotaPerdida.get();

        //List<MascotaPerdida> mascotasPerdidas = repositorioMascotaPerdida.buscarTodos();

        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-perdidas");
        //viewModel.put("mascotasPerdidas", mascotasPerdidas);
        return template.text();
    }*/

    public String mascotasPerdidas(Request request, Response response) throws IOException {
        RepositorioMascotaPerdida repositorioMascotaPerdida = FactoryRepositorioMascotaPerdida.get();

        List<MascotaPerdida> mascotasPerdidas = repositorioMascotaPerdida.buscarTodos();


        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-perdidas");
        Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("mascotasPerdidas", mascotasPerdidas);
        return template.apply(viewModel);
    }

    public String mascotaPerdida(Request request, Response response) throws IOException {
        RepositorioMascotaPerdida repositorioMascotaPerdida = FactoryRepositorioMascotaPerdida.get();

        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("estoy-perdido");
// TODO: no son mascotas en si, son publicaciones, ya que contienen MAS datos
//   contienen: Datos de la Mascota, Ubicacion donde fue encontrada, Autor
        int idMascota = new Integer(request.params("id"));
        System.out.println(idMascota);

        MascotaPerdida mascotaPerdida = repositorioMascotaPerdida.buscar(idMascota);
        System.out.println(new JsonMap<MascotaPerdida>(mascotaPerdida).transformar());

        Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("mascota", mascotaPerdida);

        return template.apply(viewModel);
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
