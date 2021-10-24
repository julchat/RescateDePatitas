package domain.controllers;

import domain.business.mascota.MascotaPerdida;
import domain.repositorios.RepositorioMascotaPerdida;
import domain.repositorios.factories.FactoryRepositorioMascotaPerdida;
import json.JsonMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeController {

    public ModelAndView home( Request request , Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"home.hbs");
    }

    public ModelAndView home2( Request request , Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"home2.hbs");
    }

    public ModelAndView adoptarMascota(Request request, Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"adoptar-mascota.hbs");
    }

    public ModelAndView darMascotaAdopcion(Request request, Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"mascota-adopcion.hbs");
    }

    public ModelAndView showMascotasPerdidas(Request request, Response response) {
        RepositorioMascotaPerdida repositorioMascotaPerdida = FactoryRepositorioMascotaPerdida.get();

        List<MascotaPerdida> mascotasPerdidas = repositorioMascotaPerdida.buscarTodos();

        Map<String, Object> viewModel = new HashMap<>();

        viewModel.put("mascotasPerdidas", mascotasPerdidas);
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
