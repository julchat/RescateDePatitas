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
        //Spark.before("/", authMiddleware::verificarSesion);

        Spark.get("/sign-in", loginController::showLogin, Router.engine);
        Spark.post("/sign-in", loginController::login);
        //Spark.get("/sign-in", (req, res) -> "Ingresar al Sistema");

        Spark.get("/sign-up", loginController::showRegister, Router.engine);
        //Spark.get("/sign-up", (req, res) -> "Registrarse");

        Spark.get("/logout", loginController::logout, Router.engine);

        Spark.get("/registrar-mascota", homeController::registrarMascota, Router.engine);
        //Spark.get("/registrar-mascota", (req, res) -> "Registrar una Mascota");

        Spark.get("/reportar-mascota", homeController::reportarMascota, Router.engine);
        //Spark.get("/reportar-mascota", (req, res) -> "Reportar una Mascota Perdida");

        Spark.get("/mascotas-perdidas", homeController::mascotasPerdidas, Router.engine);
        //Spark.get("/mascotas-perdidas", (req, res) -> "Publicaciones de Mascotas Perdidas");

        Spark.get("/dar-mascota-adopcion", homeController::darMascotaAdopcion, Router.engine);
        //Spark.get("/dar-mascota-adopcion", (req, res) -> "Dar en adopción a alguna Mascota");

        Spark.get("/adoptar-mascota", homeController::adoptarMascota, Router.engine);
        //Spark.get("/adoptar-mascota", (req, res) -> "Adoptar a una Mascota");


    }
}