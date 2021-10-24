package domain.controllers;

import domain.business.users.Persona;
import domain.business.users.TipoDoc;
import domain.repositorios.RepositorioUsuarios;
import domain.repositorios.factories.FactoryRepositorioPersonas;
import domain.repositorios.factories.FactoryRepositorioUsuarios;
import domain.repositorios.RepositorioPersonas;
import domain.security.TipoRol;
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
import java.util.stream.Collectors;

public class UsuarioController {
    private RepositorioUsuarios repositorioUsuarios = FactoryRepositorioUsuarios.get();
    private RepositorioPersonas repositorioPersonas = FactoryRepositorioPersonas.get();

    private void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = repositorioUsuarios.buscar(request.session().attribute("id"));
            parametros.put("usuario", usuario);
        }
    }

    public ModelAndView showRegistrarUsuario(Request request, Response response){
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"sign-up.hbs");
    }

    public Response registrarUsuario(Request request, Response response){

        String nombreUsuario = request.queryParams("userName");
        String password = request.queryParams("userPassword");
        String passwordConfirm = request.queryParams("passConf");

        try {
            Usuario usuario = repositorioUsuarios.buscarUsuario(nombreUsuario);

            System.out.println("Existe dicho usuario.");
            //Todo: tirar mensaje de que existe el usuario
            response.redirect("/sign-up");
        }
        catch (Exception e) {
            System.out.println("No existe dicho usuario asi que puedo utilizarlo.");

            ValidadorPassword validador = new ValidadorPassword();
            if(validador.esValida(nombreUsuario, password)) {
                System.out.println("La contraseña es válida.");

                if(password.equals(passwordConfirm)) {
                    System.out.println("La contraseña coincide con la confirmación.");
                    // Todo: tal vez toda esta parte que continua se puede hacer en otra pantalla
                    // de ser asi, entonces response.redirect("/registrar-persona") para completar los datos de la Persona

                    Usuario nuevoUsuario = new Usuario();
                    nuevoUsuario.setNombreUsuario(nombreUsuario);

                    nuevoUsuario.setRol(new User());
                    nuevoUsuario.setTipoRol(TipoRol.USER);

                    // Todo: llamar a otra pantalla para crear la persona y despues hacer un update del Usuario con la nueva Persona
                    //nuevoUsuario.setPersona(new Persona());
                    //this.registrarPersona(nuevoUsuario.getPersona(), request);
                    //repositorioPersonas.agregar(nuevoUsuario.getPersona());

                    this.repositorioUsuarios.guardarUsuario(nuevoUsuario, password);
                    // Todo: tirar mensaje que se creo el usuario de forma satisfactoria
                    System.out.println("Se ha creado el usuario de forma satisfactoria!!");
                    response.redirect("/");
                    //response.redirect("/sign-up/registrar-persona");
                }
                else {
                    // Todo: en este caso tirar que la contraseña y la confirmacion no son iguales
                    System.out.println("Las contraseñas son distintas.");
                    response.redirect("/sign-up");
                }
            }
            else {
                PasswordStatus passwordStatus = PasswordStatus.getInstance();
                // Todo: tirar mensajes de acuerdo a los errores que comete dicha contraseña
                List<String> lista = validador.verificarPassword(nombreUsuario, password);
                List<String> listaFiltrada = lista.stream().filter(s -> !s.equals(passwordStatus.getStatusOK())).collect(Collectors.toList());

                // Por ahora los imprime en la consola, habria que llevar este mensaje a la pantalla
                for(String string : listaFiltrada) {
                    System.out.println(string);
                }

                response.redirect("/sign-up");
            }
        }
        finally {
            return response;
        }
    }

    public void registrarPersona(Persona persona, Request request){
        
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

        if(request.queryParams("tipoDoc") != null){
            persona.setTipoDocumento(TipoDoc.valueOf(request.queryParams("tipoDoc")));
        }

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
        Usuario usuario = this.repositorioUsuarios.buscar(new Integer(request.params("id")));
        this.repositorioUsuarios.eliminar(usuario);
        return response;
    }

}
