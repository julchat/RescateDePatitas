package domain.controllers;

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

    public String obtenerPerfil(Request request, Response response) {
        System.out.println("OBTENIENDO EL PERFIL ----------------------------");
        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
        Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
        System.out.println("Login: " + sesionUsuario.getNombreUsuario());

        Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());
        System.out.println(usuario);

        //System.out.println(new Gson().toJson(usuario));
// Todo: por mas que le cambie en la BD de Persona a Duenio, el problema es que un usuario
//      esta relacionado con una PERSONA, asi que ese campo esta limitado a ese tipo de dato
//      en el caso de que se guarde un Duenio directamente a la BD, este queda como Duenio, y si lo asociamos
//      a un usuario, tiene que ser si o si una Persona
//      Prueba: si una Persona es de tipo Duenio, tira error, pero si es de tipo Persona puede obtener los datos del perfil

        if(usuario.getPersona().getClass() == Persona.class) {
            Persona persona = repositorioPersonas.buscar(usuario.getPersona().getId());
            System.out.println(persona);

            response.status(200);
            //System.out.println(new Gson().toJson(persona));

            return new Gson().toJson(persona);
        }
        else {
            response.status(204);
            return null;
        }
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
