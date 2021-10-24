package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
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

    public ModelAndView reportarMascota(Request request, Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"reportar-mascota.hbs");
    }

    public ModelAndView adoptarMascota(Request request, Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"adoptar-mascota.hbs");
    }

    public ModelAndView darMascotaAdopcion(Request request, Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"mascota-adopcion.hbs");
    }

    public ModelAndView mascotasPerdidas(Request request, Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"mascotas-perdidas.hbs");
    }

}
