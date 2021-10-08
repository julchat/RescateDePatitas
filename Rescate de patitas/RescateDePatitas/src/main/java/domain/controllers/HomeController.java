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

}
