package domain.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.gson.Gson;
import domain.business.Sistema;
import domain.business.caracteristicas.Caracteristica;
import domain.business.mascota.Chapa;
import domain.business.mascota.Mascota;
import domain.business.publicaciones.*;
import domain.business.users.Persona;
import domain.repositorios.*;
import domain.repositorios.factories.*;
import domain.security.Usuario;
import json.*;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApiRestController {
    private RepositorioUsuarios repositorioUsuarios = FactoryRepositorioUsuarios.get();
    private RepositorioPersonas repositorioPersonas = FactoryRepositorioPersonas.get();
    private RepositorioContactos repositorioContactos = FactoryRepositorioContacto.get();
    private RepositorioChapas repositorioChapas = FactoryRepositorioChapas.get();
    private RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();
    private RepositorioCaracteristicas repositorioCaracteristicas = FactoryRepositorioCaracteristicas.get();

    private RepositorioPublicaciones repositorioPublicaciones = FactoryRepositorioPublicaciones.get();
    private RepositorioPubliMascotaPerdida repositorioPubliMascotaPerdida = FactoryRepositorioPubliMascotaPerdida.get();
    private RepositorioPubliMascotaEnAdopcion repositorioPubliMascotaEnAdopcion = FactoryRepositorioPubliMascotaEnAdopcion.get();


    public String obtenerPerfil(Request request, Response response) {
        System.out.println("OBTENIENDO EL PERFIL ----------------------------");
        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
        Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
        System.out.println("Login: " + sesionUsuario.getNombreUsuario());

        Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());
        System.out.println(usuario);

        Persona persona = repositorioPersonas.buscar(usuario.getPersona().getId());

        response.status(200);
        System.out.println(new Gson().toJson(persona));

        return new Gson().toJson(persona);

    }

    public String obtenerUsuario(Request request, Response response)  {
        System.out.println("OBTENIENDO EL USUARIO ----------------------------");
        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
        Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
        System.out.println("Login: " + sesionUsuario.getNombreUsuario());

        Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());

        response.status(200);
        System.out.println(new Gson().toJson(usuario));

        return new Gson().toJson(usuario);
    }

    public String obtenerRol(Request request, Response response) {
        System.out.println("OBTENIENDO EL ROL ----------------------------");
        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
        Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
        System.out.println("Login: " + sesionUsuario.getNombreUsuario());

        Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());

        response.status(200);
        System.out.println(new Mensaje(usuario.getTipoRol().toString()).transformar());

        return new Mensaje(usuario.getTipoRol().toString()).transformar();
    }

    public String obtenerMascotasPorUser(Request request, Response response) {
        System.out.println("OBTENIENDO MASCOTAS DEL USUARIO ----------------------------");
        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        try {
            Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
            Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
            System.out.println("Login: " + sesionUsuario.getNombreUsuario());

            Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());

            Persona duenio = repositorioPersonas.buscar(usuario.getPersona().getId());

            List<Chapa> chapas = repositorioChapas.buscarTodos();
            List<Chapa> mascotasACargo = chapas.stream().filter(chapa -> chapa.getDuenio().getId() == duenio.getId()).collect(Collectors.toList());

            List<Mascota> mascotas = new ArrayList<>();
            for(Chapa chapa : mascotasACargo) {
                mascotas.add(chapa.getMascota());
            }

            if(mascotas.isEmpty()) {
                System.out.println("No se encontraron mascotas registradas.");
                response.status(204);
                return new Mensaje("No se encontraron mascotas registradas.").transformar();
            }
            else {
                System.out.println(JsonController.transformar(mascotas));
                response.status(200);
                return JsonController.transformar(mascotas);
            }
        }
        catch (NullPointerException e) {
            System.out.println("No se encontraron mascotas registradas.");
            response.status(204);
            return new Mensaje("No se encontraron mascotas registradas.").transformar();
        }
    }


    public String obtenerPublicaciones(Request request, Response response) {
        Sistema miSistema = Sistema.getInstance();

        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        try {
            Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
            Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
            System.out.println("Login: " + sesionUsuario.getNombreUsuario());

            Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());

            if(miSistema.validarRol(usuario.getTipoRol()).puedoAprobarPublicaciones()) {
                System.out.println("Validando permisos...");
                List<Publicacion> publicaciones = repositorioPublicaciones.buscarTodos();
                List<Publicacion> publicacionesPendientes = publicaciones.stream().filter(publicacion -> publicacion.getEstado().equals(Estados.PENDIENTE)).collect(Collectors.toList());

                System.out.println(JsonController.transformar(publicacionesPendientes));

                response.status(200);
                return JsonController.transformar(publicacionesPendientes);
            }
            else {
                System.out.println("No tiene permisos suficientes.");
                response.status(203);
                return new Mensaje("No hay permisos suficientes.").transformar();
            }
        }
        catch (NullPointerException e) {
            System.out.println("No tiene permisos suficientes.");
            response.status(203);
            return new Mensaje("No hay permisos suficientes.").transformar();
        }
    }

    public String obtenerPublicacion(Request request, Response response) {
        Sistema miSistema = Sistema.getInstance();

        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        try {
            Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
            Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
            System.out.println("Login: " + sesionUsuario.getNombreUsuario());

            Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());

            if(miSistema.validarRol(usuario.getTipoRol()).puedoAprobarPublicaciones()) {
                System.out.println("Validando permisos...");

                int idPublicacion = new Integer(request.params("id"));
                System.out.println(idPublicacion);

                PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion = repositorioPubliMascotaEnAdopcion.buscar(idPublicacion);

                if(publicacionMascotaEnAdopcion == null) {
                    PublicacionMascotaPerdida publicacionMascotaPerdida = repositorioPubliMascotaPerdida.buscar(idPublicacion);

                    if(publicacionMascotaPerdida == null) {
                        response.status(404);
                        return new Mensaje("La página no existe.").transformar();
                    }
                    else{
                        System.out.println(JsonController.transformar(publicacionMascotaPerdida));

                        response.status(200);
                        return JsonController.transformar(publicacionMascotaPerdida);
                    }
                }
                else {
                    System.out.println(JsonController.transformar(publicacionMascotaEnAdopcion));

                    response.status(200);
                    return JsonController.transformar(publicacionMascotaEnAdopcion);
                }
            }
            else {
                System.out.println("No tiene permisos suficientes.");
                response.status(203);
                return new Mensaje("No hay permisos suficientes.").transformar();
            }
        }
        catch (NullPointerException e) {
            System.out.println("No tiene permisos suficientes.");
            response.status(203);
            return new Mensaje("No hay permisos suficientes.").transformar();
        }
    }

    public String obtenerPerfilParaRegistrarMascota(Request request, Response response) {
        System.out.println("OBTENIENDO EL PERFIL ----------------------------");
        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        try {
            Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
            Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
            System.out.println("Login: " + sesionUsuario.getNombreUsuario());

            Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());

            if(usuario.getPersona().getClass() == Persona.class) {
                Persona persona = repositorioPersonas.buscar(usuario.getPersona().getId());

                RepositorioCaracteristicas repositorioCaracteristicas = FactoryRepositorioCaracteristicas.get();
                List<Caracteristica> caracteristicas = repositorioCaracteristicas.buscarTodos();
                List<Caracteristica> caracteristicasVisibles = caracteristicas.stream().filter(caracteristica -> caracteristica.isEsVisible()).collect(Collectors.toList());
                System.out.println(JsonController.transformar(caracteristicasVisibles));

                FormRegUser formulario = new FormRegUser(persona, caracteristicasVisibles);

                response.status(200);
                System.out.println(new Gson().toJson(formulario));

                return new Gson().toJson(formulario);
            }
            else if(usuario.getPersona().getClass() == Persona.class) {
                Persona persona = repositorioPersonas.buscar(usuario.getPersona().getId());

                RepositorioCaracteristicas repositorioCaracteristicas = FactoryRepositorioCaracteristicas.get();
                List<Caracteristica> caracteristicas = repositorioCaracteristicas.buscarTodos();
                List<Caracteristica> caracteristicasVisibles = caracteristicas.stream().filter(caracteristica -> caracteristica.isEsVisible()).collect(Collectors.toList());
                System.out.println(JsonController.transformar(caracteristicasVisibles));

                FormRegUser formulario = new FormRegUser(persona, caracteristicasVisibles);

                response.status(200);
                System.out.println(new Gson().toJson(formulario));

                return new Gson().toJson(formulario);
            }
            else if(usuario.getPersona().getClass() == Persona.class) {
                Persona persona = repositorioPersonas.buscar(usuario.getPersona().getId());

                RepositorioCaracteristicas repositorioCaracteristicas = FactoryRepositorioCaracteristicas.get();
                List<Caracteristica> caracteristicas = repositorioCaracteristicas.buscarTodos();
                List<Caracteristica> caracteristicasVisibles = caracteristicas.stream().filter(caracteristica -> caracteristica.isEsVisible()).collect(Collectors.toList());
                System.out.println(JsonController.transformar(caracteristicasVisibles));

                FormRegUser formulario = new FormRegUser(persona, caracteristicasVisibles);

                response.status(200);
                System.out.println(new Gson().toJson(formulario));

                return new Gson().toJson(formulario);
            }
        }
        catch (NullPointerException e) {
            RepositorioCaracteristicas repositorioCaracteristicas = FactoryRepositorioCaracteristicas.get();
            List<Caracteristica> caracteristicas = repositorioCaracteristicas.buscarTodos();
            List<Caracteristica> caracteristicasVisibles = caracteristicas.stream().filter(caracteristica -> caracteristica.isEsVisible()).collect(Collectors.toList());

            System.out.println(JsonController.transformar(caracteristicasVisibles));

            FormRegUser formulario = new FormRegUser(null, caracteristicasVisibles);

            response.status(200);
            System.out.println(new Gson().toJson(formulario));

            return new Gson().toJson(formulario);
        }
        return null;
    }


    public String obtenerPerfilParaDarAdopcion(Request request, Response response) {
        System.out.println("OBTENIENDO EL PERFIL ----------------------------");
        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        try {
            Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
            Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
            System.out.println("Login: " + sesionUsuario.getNombreUsuario());

            Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());

            if(usuario.getPersona().getClass() == Persona.class) {
                Persona persona = repositorioPersonas.buscar(usuario.getPersona().getId());

                RepositorioPreguntas repositorioPreguntas = FactoryRepositorioPreguntas.get();
                List<Pregunta> preguntas = repositorioPreguntas.buscarTodos();
                System.out.println(JsonController.transformar(preguntas));

                RepositorioCaracteristicas repositorioCaracteristicas = FactoryRepositorioCaracteristicas.get();
                List<Caracteristica> caracteristicas = repositorioCaracteristicas.buscarTodos();
                List<Caracteristica> caracteristicasVisibles = caracteristicas.stream().filter(caracteristica -> caracteristica.isEsVisible()).collect(Collectors.toList());
                System.out.println(JsonController.transformar(caracteristicasVisibles));

                FormAdopUser formulario = new FormAdopUser(persona, caracteristicasVisibles, preguntas);

                response.status(200);
                System.out.println(new Gson().toJson(formulario));

                return new Gson().toJson(formulario);
            }
            else if(usuario.getPersona().getClass() == Persona.class) {
                Persona persona = repositorioPersonas.buscar(usuario.getPersona().getId());

                RepositorioCaracteristicas repositorioCaracteristicas = FactoryRepositorioCaracteristicas.get();
                List<Caracteristica> caracteristicas = repositorioCaracteristicas.buscarTodos();
                List<Caracteristica> caracteristicasVisibles = caracteristicas.stream().filter(caracteristica -> caracteristica.isEsVisible()).collect(Collectors.toList());
                System.out.println(JsonController.transformar(caracteristicasVisibles));

                FormRegUser formulario = new FormRegUser(persona, caracteristicasVisibles);

                response.status(200);
                System.out.println(new Gson().toJson(formulario));

                return new Gson().toJson(formulario);
            }
            else if(usuario.getPersona().getClass() == Persona.class) {
                Persona persona = repositorioPersonas.buscar(usuario.getPersona().getId());

                RepositorioPreguntas repositorioPreguntas = FactoryRepositorioPreguntas.get();
                List<Pregunta> preguntas = repositorioPreguntas.buscarTodos();
                System.out.println(JsonController.transformar(preguntas));

                RepositorioCaracteristicas repositorioCaracteristicas = FactoryRepositorioCaracteristicas.get();
                List<Caracteristica> caracteristicas = repositorioCaracteristicas.buscarTodos();
                List<Caracteristica> caracteristicasVisibles = caracteristicas.stream().filter(caracteristica -> caracteristica.isEsVisible()).collect(Collectors.toList());
                System.out.println(JsonController.transformar(caracteristicasVisibles));

                FormAdopUser formulario = new FormAdopUser(persona, caracteristicasVisibles, preguntas);

                response.status(200);
                System.out.println(new Gson().toJson(formulario));

                return new Gson().toJson(formulario);
            }
        }
        catch (NullPointerException e) {

            RepositorioPreguntas repositorioPreguntas = FactoryRepositorioPreguntas.get();
            List<Pregunta> preguntas = repositorioPreguntas.buscarTodos();
            System.out.println(JsonController.transformar(preguntas));

            RepositorioCaracteristicas repositorioCaracteristicas = FactoryRepositorioCaracteristicas.get();
            List<Caracteristica> caracteristicas = repositorioCaracteristicas.buscarTodos();
            List<Caracteristica> caracteristicasVisibles = caracteristicas.stream().filter(caracteristica -> caracteristica.isEsVisible()).collect(Collectors.toList());
            System.out.println(JsonController.transformar(caracteristicasVisibles));

            FormAdopUser formulario = new FormAdopUser(null, caracteristicasVisibles, preguntas);

            response.status(200);
            System.out.println(new Gson().toJson(formulario));

            return new Gson().toJson(formulario);
        }
        return null;
    }


    public String obtenerMascotaEnAdopcion(Request request, Response response) {

        String idSesion = request.headers("Authorization");
        System.out.println("ID SESION: " + idSesion);

        if(idSesion == null) {
            System.out.println("No tiene permisos para acceder.");
            response.status(203);
            return new Mensaje("No tiene permisos para acceder").transformar();
        }
        else {
            System.out.println(request.params("id"));
            int idMascota = new Integer(request.params("id"));
            System.out.println(idMascota);

            RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();
            Mascota mascota = repositorioMascotas.buscar(idMascota);
            System.out.println(JsonController.transformar(mascota));

            if(mascota == null) {
                System.out.println("No existe la página.");
                response.status(404);
                return new Mensaje("No existe la página a la que intenta ingresar.").transformar();
            }
            else {

                RepositorioUsuarios repositorioUsuarios = FactoryRepositorioUsuarios.get();
                RepositorioPersonas repositorioPersonas = FactoryRepositorioPersonas.get();
                RepositorioChapas repositorioChapas = FactoryRepositorioChapas.get();

                try {
                    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
                    Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");

                    Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());
                    Persona persona = repositorioPersonas.buscar(usuario.getPersona().getId());
                    System.out.println(JsonController.transformar(persona));

                    Persona duenio = repositorioChapas.buscarDuenio(idMascota);
                    System.out.println(JsonController.transformar(duenio));

                    if(persona.getId() != duenio.getId()){
                        System.out.println("No tiene permisos para acceder.");
                        response.status(203);
                        return new Mensaje("No tiene permisos para acceder").transformar();
                    }
                    else {
                        RepositorioPreguntas repositorioPreguntas = FactoryRepositorioPreguntas.get();
                        List<Pregunta> preguntas = repositorioPreguntas.buscarTodos();
                        System.out.println(JsonController.transformar(preguntas));

                        FormAdopPet formulario = new FormAdopPet(duenio, mascota, preguntas);
                        System.out.println(JsonController.transformar(formulario));

                        response.status(200);
                        return JsonController.transformar(formulario);
                    }
                }
                catch (NullPointerException e) {
                    System.out.println("No tiene permisos para acceder.");
                    response.status(203);
                    return new Mensaje("No tiene permisos para acceder").transformar();
                }
            }
        }
    }


    public String permiteAdministrar(Request request, Response response) {
        Sistema miSistema = Sistema.getInstance();

        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        try {
            Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
            Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
            System.out.println("Login: " + sesionUsuario.getNombreUsuario());

            Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());

            if(miSistema.validarRol(usuario.getTipoRol()).puedoCambiarEstandares()) {
                System.out.println("Validando permisos...");
                List<Caracteristica> caracteristicas = repositorioCaracteristicas.buscarTodos();

                response.status(200);
                return JsonController.transformar(caracteristicas);
            }
            else {
                System.out.println("No tiene permisos suficientes.");
                response.status(203);
                return new Mensaje("No hay permisos suficientes.").transformar();
            }
        }
        catch (NullPointerException e) {
            System.out.println("No tiene permisos suficientes.");
            response.status(203);
            return new Mensaje("No hay permisos suficientes.").transformar();
        }
    }


    public String permiteAgregarAdmin(Request request, Response response) {
        Sistema miSistema = Sistema.getInstance();

        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        try {
            Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
            Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
            System.out.println("Login: " + sesionUsuario.getNombreUsuario());

            Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());

            if(miSistema.validarRol(usuario.getTipoRol()).puedoCrearAdministradores()) {
                System.out.println("Validando permisos...");
                List<Usuario> usuariosRegistrados = repositorioUsuarios.buscarTodos();
                List<FormUsuarioRol> usuariosAMostrar = new ArrayList<>();

                for(Usuario usuarioRegistrado : usuariosRegistrados) {
                    if(usuarioRegistrado.getNombreUsuario() != usuario.getNombreUsuario()) {
                        FormUsuarioRol usuarioAMostrar = new FormUsuarioRol(String.valueOf(usuarioRegistrado.getId()), usuarioRegistrado.getNombreUsuario(), usuarioRegistrado.getTipoRol().toString());
                        usuariosAMostrar.add(usuarioAMostrar);
                    }
                }
                System.out.println(JsonController.transformar(usuariosAMostrar));

                response.status(200);
                return JsonController.transformar(usuariosAMostrar);
            }
            else {
                System.out.println("No tiene permisos suficientes.");
                response.status(203);
                return new Mensaje("No hay permisos suficientes.").transformar();
            }
        }
        catch (NullPointerException e) {
            System.out.println("No tiene permisos suficientes.");
            response.status(203);
            return new Mensaje("No hay permisos suficientes.").transformar();
        }
    }


    public String mascotasPerdidas(Request request, Response response) {
        RepositorioPubliMascotaPerdida repositorioPubliMascotaPerdida = FactoryRepositorioPubliMascotaPerdida.get();

        List<PublicacionMascotaPerdida> publicaciones = repositorioPubliMascotaPerdida.buscarTodos();
        List<PublicacionMascotaPerdida> publicacionesAceptadas = publicaciones.stream().filter(publicacion -> publicacion.getEstado().equals(Estados.APROBADA)).collect(Collectors.toList());
        System.out.println(JsonController.transformar(publicacionesAceptadas));

        return JsonController.transformar(publicacionesAceptadas);
    }

    public String mascotasEnAdopcion(Request request, Response response) {
        RepositorioPubliMascotaEnAdopcion repositorioPubliMascotaEnAdopcion = FactoryRepositorioPubliMascotaEnAdopcion.get();

        List<PublicacionMascotaEnAdopcion> publicaciones = repositorioPubliMascotaEnAdopcion.buscarTodos();
        List<PublicacionMascotaEnAdopcion> publicacionesAceptadas = publicaciones.stream().filter(publicacion -> publicacion.getEstado().equals(Estados.APROBADA)).collect(Collectors.toList());
        System.out.println(JsonController.transformar(publicacionesAceptadas));

        return JsonController.transformar(publicacionesAceptadas);
    }
}
