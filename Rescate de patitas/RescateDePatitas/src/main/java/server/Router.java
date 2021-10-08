package server;

import domain.controllers.HomeController;
import domain.controllers.LoginController;
import domain.middleware.AuthMiddleware;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
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
        HomeController homeController = new HomeController();
        AuthMiddleware authMiddleware = new AuthMiddleware();

        Spark.get("/", homeController::home, Router.engine);
        Spark.before("/", authMiddleware::verificarSesion);
        Spark.get("/login", loginController::showLogin, Router.engine);
        Spark.post("/login", loginController::login);
        Spark.get("/logout", loginController::logout, Router.engine);
    }
}