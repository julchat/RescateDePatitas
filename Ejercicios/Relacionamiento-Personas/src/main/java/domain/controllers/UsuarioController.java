package domain.controllers;

import domain.model.User;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioController {
    private Repositorio<User> repositorio;

    public UsuarioController(){
        this.repositorio = FactoryRepositorio.get(User.class);
    }

    private void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            User usuario = repositorio.buscar(request.session().attribute("id"));
            parametros.put("usuario", usuario);
        }
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<User> usuarios = this.repositorio.buscarTodos();
        parametros.put("usuarios", usuarios);
        asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "usuarios.hbs");
    }

    public ModelAndView mostrar(Request request, Response response){
        User usuario = this.repositorio.buscar(new Integer(request.params("id")));
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", usuario);
        return new ModelAndView(parametros, "usuario.hbs");
    }

    public Response modificar(Request request, Response response){
        User usuario = this.repositorio.buscar(new Integer(request.params("id")));
        asignarAtributosA(usuario, request);
        this.repositorio.modificar(usuario);
        response.redirect("/usuarios");
        return response;
    }

    private void asignarAtributosA(User usuario, Request request){

       /* if(request.queryParams("nombre") != null){
            usuario.setNombre(request.queryParams("nombre"));
        }

        if(request.queryParams("apellido") != null){
            usuario.setApellido(request.queryParams("apellido"));
        }

        if(request.queryParams("nombreDeUsuario") != null){
            usuario.setUsuario(request.queryParams("nombreDeUsuario"));
        }

        if(request.queryParams("legajo") != null){
            int legajo = new Integer(request.queryParams("legajo"));
            usuario.setLegajo(legajo);
        }

        if(request.queryParams("fechaDeNacimiento") != null && !request.queryParams("fechaDeNacimiento").isEmpty()){
            LocalDate fechaDeNacimiento = LocalDate.parse(request.queryParams("fechaDeNacimiento"));
            usuario.setFechaDeNacimiento(fechaDeNacimiento);
        }

        if(request.queryParams("rol") != null){
            Repositorio<Rol> repoRol = FactoryRepositorio.get(Rol.class);
            Rol unRol = repoRol.buscar(new Integer(request.queryParams("rol")));
            usuario.setRol(unRol);
        }*/
    }

    public ModelAndView crear(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros, "usuario.hbs");
    }

    public Response guardar(Request request, Response response){
        User usuario = new User();
        asignarAtributosA(usuario, request);
        this.repositorio.agregar(usuario);
        response.redirect("/usuarios");
        return response;
    }

    public Response eliminar(Request request, Response response){
        User usuario = this.repositorio.buscar(new Integer(request.params("id")));
        this.repositorio.eliminar(usuario);
        return response;
    }
}