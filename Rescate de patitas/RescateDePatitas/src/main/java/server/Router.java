package server;

import domain.controllers.LoginController;
import domain.middleware.AuthMiddleware;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }


    // todos los archivos estaticos y publicos (js, css, img) estan en la carpeta /public
    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){
        LoginController loginController = new LoginController();
        AuthMiddleware authMiddleware = new AuthMiddleware();

        Spark.get("/", loginController::inicio, Router.engine);

        Spark.before("/", authMiddleware::verificarSesion);

    }
}