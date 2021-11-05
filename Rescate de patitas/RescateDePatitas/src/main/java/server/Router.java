package server;

import domain.controllers.FormularioController;
import domain.controllers.HomeController;
import domain.controllers.LoginController;
import domain.controllers.UsuarioController;
import domain.middleware.AuthMiddleware;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;

import static spark.Spark.options;
import static spark.route.HttpMethod.before;

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

        Spark.get("/", homeController::inicio);
        Spark.before("/inicio", authMiddleware::verificarSesion);
        Spark.get("/home", homeController::homeSesion);

        Spark.get("/iniciar-sesion", loginController::iniciarSesion);
        Spark.post("/iniciar-sesion", loginController::iniciarSesionPost);

        Spark.get("/logout", loginController::logout);

        Spark.get("/registrarse", homeController::registrarse);
        Spark.post("/registrarse", usuarioController::registrarUsuario);

        //Spark.get("/editar-perfil", homeController::showEditarPerfil);
        Spark.get("/editar-perfil", usuarioController::editarPerfil);

        //Spark.get("/editar-perfil/:id", usuarioController::editarPerfil);
        //Spark.put("/editar-perfil/:id", usuarioController::editarPerfilPost);

        Spark.get("/registrar-mascota", homeController::registrarMascotaPerdida);
        //Spark.post("/registrar-mascota", formularioController::registrarMascotaPost);

        // Si escaneo el codigo QR me deberia redirigir a esta direccion, indicando el ID de chapa que contendria el mismo QR
        Spark.get("/reportar-mascota/:id", formularioController::showMascotaPerdidaChapita);
        Spark.post("/reportar-mascota/:id", formularioController::mascotaPerdidaChapita);

        // En el caso de no tener Chapita, se ingresa por esta direccion y se completan los Formularios correspondientes
        Spark.get("/reportar-mascota", homeController::reportarMascotaPerdida);
        //Spark.post("/reportar-mascota", formularioController::reportarMascotaPost);

        Spark.get("/mascotas-perdidas", homeController::mascotasPerdidas);

        Spark.get("/dar-mascota-adopcion", homeController::darMascotaAdopcion);

        Spark.get("/mascotas-en-adopcion", homeController::mascotasEnAdopcion);

        Spark.get("/mascotas-en-adopcion/adoptar-mascota/:id", homeController::adoptarMascota);

        Spark.get("/mascotas-en-adopcion/busqueda-mascota-ideal", homeController::buscarMascotaIdeal);

        Spark.get("/notificar-duenio/:id", homeController::notificarDuenio);
        Spark.get("/notificar-rescatista/:id", homeController::notificarRescatista);

        Spark.options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });
    }
}