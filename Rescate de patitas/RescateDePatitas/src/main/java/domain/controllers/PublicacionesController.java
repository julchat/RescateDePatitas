package domain.controllers;

import domain.business.publicaciones.Publicacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class PublicacionesController {

    public ModelAndView showPublicacion(Request request , Response response) {
        Map<String, Object> viewModel = new HashMap<>();

        //Publicacion publicacion = repoPublicaciones

        return new ModelAndView(viewModel,"publicacion.hbs");
    }
}
