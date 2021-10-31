package domain.controllers;

import domain.business.mascota.MascotaPerdida;
import domain.repositorios.RepositorioMascotaPerdida;
import domain.repositorios.factories.FactoryRepositorioMascotaPerdida;
import domain.security.Admin;
import domain.security.TipoRol;
import domain.security.Usuario;
import json.JsonMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeController {

    public String prueba(Request request, Response response) {
        response.type("application/json");
        return new JsonMap<Usuario>(new Usuario("admin", "admin", TipoRol.ADMIN, new Admin(), null),"MENSAJE DE PRUEBA").transformar();
    }

    public ModelAndView home( Request request , Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"home.hbs");
    }

    public ModelAndView showHomeUser( Request request , Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"homeUser.hbs");
    }

    /*public ModelAndView showHomeAdmin( Request request , Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"homeAdmin.hbs");
    }

    public ModelAndView showHomeModer( Request request , Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"homeModer.hbs");
    }*/

    public ModelAndView showAdoptarMascota(Request request, Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"mascotas-adopcion.hbs");
    }

    public ModelAndView darMascotaAdopcion(Request request, Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"dar-mascota-adopcion.hbs");
    }

    public ModelAndView showMascotasPerdidas(Request request, Response response) {
        //RepositorioMascotaPerdida repositorioMascotaPerdida = FactoryRepositorioMascotaPerdida.get();

        //List<MascotaPerdida> mascotasPerdidas = repositorioMascotaPerdida.buscarTodos();

        Map<String, Object> viewModel = new HashMap<>();

        //viewModel.put("mascotasPerdidas", mascotasPerdidas);
        return new ModelAndView(viewModel,"mascotas-perdidas.hbs");
    }

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
    }

}
