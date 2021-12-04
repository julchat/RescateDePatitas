package server;

import domain.controllers.*;
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
        ApiRestController apiRestController = new ApiRestController();
        AuthMiddleware authMiddleware = new AuthMiddleware();
        FormularioController formularioController = new FormularioController();
        PublicacionesController publicacionesController = new PublicacionesController();


        // API REST
        Spark.get("/api/perfil", apiRestController::obtenerPerfil);
        Spark.get("/api/user", apiRestController::obtenerRol);
        Spark.get("/api/mascotasUser", apiRestController::obtenerMascotasPorUser);
        Spark.get("/api/publicaciones", apiRestController::obtenerPublicaciones);
        Spark.get("/api/publicacion/:id", apiRestController::obtenerPublicacion);
        Spark.get("/api/cambiar-caracteristicas", apiRestController::permiteAdministrar);
        Spark.get("/api/perfilRegistroMascota", apiRestController::obtenerPerfilParaRegistrarMascota);
        Spark.get("/api/perfilDarEnAdopcion", apiRestController::obtenerPerfilParaDarAdopcion);
        Spark.get("/api/darMascotaEnAdopcionUser/:id", apiRestController::obtenerMascotaEnAdopcion);
        Spark.get("/api/agregar-admin", apiRestController::permiteAgregarAdmin);
        Spark.get("/api/mascotas-perdidas", apiRestController::mascotasPerdidas);
        Spark.get("/api/mascotas-en-adopcion", apiRestController::mascotasEnAdopcion);


        Spark.get("/", homeController::inicio);
        Spark.before("/", authMiddleware::verificarSesion);
        Spark.get("/home", homeController::inicio);
        Spark.get("/sin-permisos", homeController::sinPermisos);
        Spark.get("/no-existe", homeController::noExiste);

        Spark.get("/iniciar-sesion", homeController::iniciarSesion);
        Spark.post("/iniciar-sesion", loginController::iniciarSesion);
        Spark.get("/logout", loginController::logout);

        Spark.get("/registrarse", homeController::registrarUsuario);
        Spark.post("/registrarse", usuarioController::registrarUsuario);

        Spark.get("/editar-perfil", homeController::editarPerfil);
        //Spark.post("/editar-perfil", usuarioController::editarPerfilPost);
        Spark.get("/mascotas-registradas", homeController::mascotasRegistradas);

        Spark.get("/registrar-mascota", homeController::registrarMascota);
        Spark.post("/registrar-mascota", formularioController::registrarMascota);

// --  Si escaneo el codigo QR me deberia redirigir a esta direccion, indicando el ID de chapa que contendria el mismo QR
        Spark.get("/reportar-mascota/:id", homeController::mascotaPerdidaChapita);
        Spark.post("/reportar-mascota/:id", formularioController::mascotaPerdidaChapita);
// -- En el caso de no tener Chapita, se ingresa por esta direccion y se completan los Formularios correspondientes
        Spark.get("/reportar-mascota", homeController::mascotaPerdida);
        Spark.post("/reportar-mascota", formularioController::mascotaPerdida);

        Spark.get("/mascotas-perdidas-pesado", homeController::mascotasPerdidasPesado);
        Spark.get("/mascotas-perdidas", homeController::mascotasPerdidas);
        Spark.get("/mascotas-perdidas/estoy-perdido/:id", homeController::estoyPerdido);
        Spark.get("/mascotas-perdidas/notificar-rescatista/:id", homeController::notificarRescatista);
        Spark.post("/mascotas-perdidas/notificar-rescatista/:id", formularioController::notificarRescatista);

        Spark.get("/mascotas-en-adopcion-pesado", homeController::mascotasEnAdopcionPesado);
        Spark.get("/mascotas-en-adopcion", homeController::mascotasEnAdopcion);
        Spark.get("/mascotas-en-adopcion/estoy-en-adopcion/:id", homeController::estoyEnAdopcion);
        Spark.get("/mascotas-en-adopcion/notificar-duenio/:id", homeController::notificarDuenio);
        Spark.post("/mascotas-en-adopcion/notificar-duenio/:id", formularioController::notificarDuenio);

        Spark.get("/mascotas-en-adopcion/busqueda-mascota-ideal", homeController::buscarMascotaIdeal);
        Spark.post("/mascotas-en-adopcion/busqueda-mascota-ideal", formularioController::buscarMascotaIdeal);

        Spark.get("/dar-mascota-adopcion", homeController::darMascotaAdopcion);
        Spark.post("/dar-mascota-adopcion", formularioController::darMascotaAdopcion);
        Spark.get("/dar-mascota-adopcion/:id", homeController::darMascotaAdopcionParticular);
        Spark.post("/dar-mascota-adopcion/:id", formularioController::darMascotaAdopcionParticular);

        // Solo para los Admin
        Spark.get("/administrar-caracteristicas", homeController::adminCaracteristicas);
        Spark.post("/agregar-caracteristica", formularioController::agregarCaracteristica);
        Spark.post("/visibilizar-caractersticas", formularioController::visibilizarCaracteristicas);
        Spark.post("/ocultar-caracteristicas", formularioController::ocultarCaracteristicas);

        Spark.get("/administrar-usuarios", homeController::adminUsuarios);
        Spark.post("/agregar-admin", formularioController::agregarNuevoAdmin);
        Spark.post("/quitar-admin", formularioController::quitarAdmin);

        // Solo para los Moderadores
        Spark.get("/publicaciones-pendientes", homeController::publicacionesPendientes);
        Spark.get("/publicaciones-pendientes/:id", publicacionesController::publicacion);
        Spark.post("/publicaciones-pendientes/:id", publicacionesController::administrarPublicacion);
        Spark.post("/aprobar-publicacion", publicacionesController::aprobarPublicacion);
        Spark.post("/rechazar-publicacion", publicacionesController::rechazarPublicacion);

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