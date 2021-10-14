package domain.controllers;

import domain.business.users.Persona;
import domain.repositorios.Repositorio;
import domain.repositorios.RepositorioUsuarios;
import domain.repositorios.factories.FactoryRepositorio;
import domain.repositorios.factories.FactoryRepositorioPersonas;
import domain.repositorios.factories.FactoryRepositorioUsuarios;
import domain.repositorios.factories.RepositorioPersonas;
import domain.security.Rol;
import domain.security.User;
import domain.security.Usuario;
import domain.security.password.PasswordStatus;
import domain.security.password.ValidadorPassword;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioController {
    private RepositorioUsuarios repositorio;

    public UsuarioController() {
        this.repositorio = FactoryRepositorioUsuarios.get();
    }

    private void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = repositorio.buscar(request.session().attribute("id"));
            parametros.put("usuario", usuario);
        }
    }

    public ModelAndView showRegister(Request request, Response response){
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"sign-up.hbs");
    }

    public Response registrarUsuario(Request request, Response response){

        if(this.repositorio.existe(request.queryParams("user"))) {
            System.out.println("No existe dicho usuario.");

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombreUsuario(request.queryParams("user"));
            nuevoUsuario.setContrasenia(request.queryParams("password"));

            ValidadorPassword validador = new ValidadorPassword();
            if(validador.esValida(nuevoUsuario.getNombreUsuario(), nuevoUsuario.getContrasenia())) {

                if(nuevoUsuario.getContrasenia().equals(request.queryParams("passwordConfirm"))) {
                // Todo: tal vez toda esta parte que continua se puede hacer en otra pantalla
                // de ser asi, entonces response.redirect("/registrar-persona") para completar los datos de la Persona
                    nuevoUsuario.setRol(new User());
                    nuevoUsuario.setPersona(new Persona());
                    this.registrarPersona(nuevoUsuario.getPersona(), request);
                    RepositorioPersonas repositorioPersonas = FactoryRepositorioPersonas.get();
                    repositorioPersonas.agregar(nuevoUsuario.getPersona());

                    this.repositorio.agregar(nuevoUsuario);
                    // Todo: tirar mensaje que se creo el usuario de forma satisfactoria
                    response.redirect("/");
                }
                else {
                    // Todo: en este caso tirar que la contraseña y la confirmacion no son iguales
                    System.out.println("Las contraseñas son distintas.");
                    return response;
                }
            }
            else {
                PasswordStatus passwordStatus = PasswordStatus.getInstance();
                // Todo: tirar mensajes de acuerdo a los errores que comete dicha contraseña
                List<String> lista = validador.verificarPassword(nuevoUsuario.getNombreUsuario(), nuevoUsuario.getContrasenia());
                lista.stream().filter(s -> !s.equals(passwordStatus.getStatusOK()));

                for(String string : lista) {
                    System.out.println(string);
                }
            }

        }
        else {
            System.out.println("Existe dicho usuario.");
            //Todo: tirar mensaje de que existe el usuario
            response.redirect("/sign-up");
        }
        return response;
    }

    private void registrarPersona(Persona persona, Request request){
        if(request.queryParams("nombre") != null){
            persona.setNombre(request.queryParams("nombre"));
        }

        if(request.queryParams("apellido") != null){
            persona.setApellido(request.queryParams("apellido"));
        }

        if(request.queryParams("fechaDeNacimiento") != null && !request.queryParams("fechaDeNacimiento").isEmpty()){
            LocalDate fechaDeNacimiento = LocalDate.parse(request.queryParams("fechaDeNacimiento"));
            persona.setFechaDeNacimiento(fechaDeNacimiento);
        }

        /*      DEBERIA DAR LO QUE ELIGE DE LA LISTA DE TIPOS DE DOC
        if(request.queryParams("tipoDoc") != null){
            persona.setTipoDocumento();
        }*/

        if(request.queryParams("nroDocumento") != null){
            persona.setNumeroDocumento(new Integer(request.queryParams("nroDocumento")));
        }

        if(request.queryParams("email") != null){
            persona.setEmail(request.queryParams("email"));
        }

        if(request.queryParams("telefono") != null){
            persona.setTelefono(request.queryParams("telefono"));
        }

        /*
        if(request.queryParams("formasDeNotifacion") != null){
            persona.setFormasDeNotificacion(request.queryParams("formasDeNotifacion"));
        }

        if(request.queryParams("contactos") != null){
            persona.setContactos(request.queryParams("contactos"));
        }*/
    }

    public Response eliminar(Request request, Response response){
        Usuario usuario = this.repositorio.buscar(new Integer(request.params("id")));
        this.repositorio.eliminar(usuario);
        return response;
    }
}
