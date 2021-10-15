package server;

import domain.controllers.FormularioController;
import domain.controllers.HomeController;
import domain.controllers.LoginController;
import domain.controllers.UsuarioController;
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

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){
        LoginController loginController = new LoginController();
        HomeController homeController = new HomeController();
        UsuarioController usuarioController = new UsuarioController();
        AuthMiddleware authMiddleware = new AuthMiddleware();
        FormularioController formularioController = new FormularioController();

        Spark.get("/", homeController::home, Router.engine);
        // Si entras, y despues actualizas la pagina, te tira que se redirecciono demasiadas veces
        //Spark.before("/", authMiddleware::verificarSesion);
        Spark.get("/home2", homeController::home2, Router.engine);

        Spark.get("/sign-in", loginController::showLogin, Router.engine);
        Spark.post("/sign-in", loginController::login);

        Spark.get("/sign-up", usuarioController::showRegister, Router.engine);
        Spark.post("/sign-up", usuarioController::registrarUsuario);

        Spark.get("/logout", loginController::logout, Router.engine);

        Spark.get("/registrar-mascota", homeController::registrarMascota, Router.engine);

        // Si escaneo el codigo QR me deberia redirigir a esta direccion, indicando el ID de chapa que contendria el mismo QR
        Spark.get("/reportar-mascota/:id", formularioController::showMascotaPerdidaChapita, Router.engine);
        Spark.post("/reportar-mascota/:id", formularioController::mascotaPerdidaChapita);

        // En el caso de no tener Chapita, se ingresa por esta direccion y se completan los Formularios correspondientes
        Spark.get("/reportar-mascota", formularioController::showMascotaPerdida, Router.engine);
        Spark.post("/reportar-mascota", formularioController::mascotaPerdida);

        Spark.get("/mascotas-perdidas", homeController::mascotasPerdidas, Router.engine);

        Spark.get("/dar-mascota-adopcion", homeController::darMascotaAdopcion, Router.engine);

        Spark.get("/adoptar-mascota", homeController::adoptarMascota, Router.engine);

    }
}