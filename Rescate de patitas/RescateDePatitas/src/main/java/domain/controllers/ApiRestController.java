package domain.controllers;

import com.google.gson.Gson;
import domain.business.mascota.Mascota;
import domain.business.users.Duenio;
import domain.business.users.Persona;
import domain.business.users.Rescatista;
import domain.repositorios.*;
import domain.repositorios.factories.*;
import domain.security.Usuario;
import json.Mensaje;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Map;

public class ApiRestController {
    private RepositorioUsuarios repositorioUsuarios = FactoryRepositorioUsuarios.get();
    private RepositorioPersonas repositorioPersonas = FactoryRepositorioPersonas.get();
    private RepositorioDuenio repositorioDuenio = FactoryRepositorioDuenio.get();
    private RepositorioRescatista repositorioRescatista = FactoryRepositorioRescatista.get();
    private RepositorioContactos repositorioContactos = FactoryRepositorioContacto.get();
    private RepositorioChapas repositorioChapas = FactoryRepositorioChapas.get();
    private RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();

    public String obtenerPerfil(Request request, Response response) {
        System.out.println("OBTENIENDO EL PERFIL ----------------------------");
        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
        Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
        System.out.println("Login: " + sesionUsuario.getNombreUsuario());

        Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());

// Todo: por mas que le cambie en la BD de Persona a Duenio, el problema es que un usuario
//      esta relacionado con una PERSONA, asi que ese campo esta limitado a ese tipo de dato
//      en el caso de que se guarde un Duenio directamente a la BD, este queda como Duenio, y si lo asociamos
//      a un usuario, tiene que ser si o si una Persona
//      Prueba: si una Persona es de tipo Duenio, tira error, pero si es de tipo Persona puede obtener los datos del perfil

        if(usuario.getPersona().getClass() == Persona.class) {
            Persona persona = repositorioPersonas.buscar(usuario.getPersona().getId());

            response.status(200);
            System.out.println(new Gson().toJson(persona));

            return new Gson().toJson(persona);
        }
        else if(usuario.getPersona().getClass() == Duenio.class) {
            Duenio persona = repositorioDuenio.buscar(usuario.getPersona().getId());

            response.status(200);
            System.out.println(new Gson().toJson(persona));

            return new Gson().toJson(persona);
        }
        else if(usuario.getPersona().getClass() == Rescatista.class) {
            Rescatista persona = repositorioRescatista.buscar(usuario.getPersona().getId());

            response.status(200);
            System.out.println(new Gson().toJson(persona));

            return new Gson().toJson(persona);
        }
        response.status(500);
        return null;
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
        System.out.println("OBTENIENDO EL USUARIO ----------------------------");
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
        System.out.println("OBTENIENDO EL USUARIO ----------------------------");
        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
        Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
        System.out.println("Login: " + sesionUsuario.getNombreUsuario());

        Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());
        System.out.println(usuario);

        List<Mascota> mascotas = repositorioMascotas.buscarTodos();

        response.status(200);
        System.out.println(new Gson().toJson(mascotas));

        return new Gson().toJson(mascotas);
    }

}
