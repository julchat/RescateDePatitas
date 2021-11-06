package domain.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.gson.Gson;
import domain.business.notificaciones.NotificadorEmail;
import domain.business.notificaciones.NotificadorSms;
import domain.business.notificaciones.NotificadorWhatsapp;
import domain.business.notificaciones.TipoNotificacion;
import domain.business.users.Contacto;
import domain.business.users.Persona;
import domain.business.users.TipoDoc;
import domain.repositorios.RepositorioContactos;
import domain.repositorios.RepositorioUsuarios;
import domain.repositorios.factories.FactoryRepositorio;
import domain.repositorios.factories.FactoryRepositorioContacto;
import domain.repositorios.factories.FactoryRepositorioPersonas;
import domain.repositorios.factories.FactoryRepositorioUsuarios;
import domain.repositorios.RepositorioPersonas;
import domain.security.TipoRol;
import domain.security.User;
import domain.security.Usuario;
import domain.security.password.PasswordStatus;
import domain.security.password.ValidadorPassword;
import json.FormUser;
import json.JsonMap;
import json.Mensaje;
import json.Sesion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsuarioController {
    private RepositorioUsuarios repositorioUsuarios = FactoryRepositorioUsuarios.get();
    private RepositorioPersonas repositorioPersonas = FactoryRepositorioPersonas.get();
    private RepositorioContactos repositorioContactos = FactoryRepositorioContacto.get();

    private void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = repositorioUsuarios.buscar(request.session().attribute("id"));
            parametros.put("usuario", usuario);
        }
    }


    public String registrarUsuario(Request request, Response response){

        FormUser formUser = new Gson().fromJson(request.body(), FormUser.class);
        System.out.println(request.body());

        String nombreUsuario = formUser.getUserName();
        String password = formUser.getPassword();
        String passwordConfirm = formUser.getPassConf();

        try {
            Usuario usuario = repositorioUsuarios.buscarUsuario(nombreUsuario);

            System.out.println("Existe dicho usuario.");
            response.status(404);
            return new Mensaje("El usuario no esta disponible.").transformar();
        }
        catch (Exception e) {
            System.out.println("No existe dicho usuario asi que puedo utilizarlo.");

            ValidadorPassword validador = new ValidadorPassword();
            if(validador.esValida(nombreUsuario, password)) {
                System.out.println("La contraseña es válida.");

                if(password.equals(passwordConfirm)) {
                    System.out.println("La contraseña coincide con la confirmación.");

                    Usuario nuevoUsuario = new Usuario();
                    nuevoUsuario.setNombreUsuario(nombreUsuario);

                    nuevoUsuario.setRol(new User());
                    nuevoUsuario.setTipoRol(TipoRol.USER);

                    nuevoUsuario.setPersona(new Persona());

                    nuevoUsuario.getPersona().setNombre(formUser.getNombre());
                    nuevoUsuario.getPersona().setApellido(formUser.getApellido());
                    LocalDate fechaDeNacimiento = LocalDate.parse(formUser.getFechaDeNacimiento());
                    nuevoUsuario.getPersona().setFechaDeNacimiento(fechaDeNacimiento);
                    nuevoUsuario.getPersona().setTipoDocumento(TipoDoc.valueOf(formUser.getTipoDoc()));
                    nuevoUsuario.getPersona().setNumeroDocumento(new Integer(formUser.getNroDocumento()));
                    nuevoUsuario.getPersona().setEmail(formUser.getEmail());
                    nuevoUsuario.getPersona().setTelefono(formUser.getTelefono());

                    if(formUser.getNotificacionSms() != "false") {
                        nuevoUsuario.getPersona().getFormasDeNotificacion().add(new NotificadorSms());
                    }

                    if(formUser.getNotificacionEmail() != "false") {
                        nuevoUsuario.getPersona().getFormasDeNotificacion().add(new NotificadorEmail());
                    }

                    if(formUser.getNotificacionWpp() != "false") {
                        nuevoUsuario.getPersona().getFormasDeNotificacion().add(new NotificadorWhatsapp());
                    }

                    Contacto contactoUnico = new Contacto();
                    contactoUnico.setNombreContacto(formUser.getContactoNombre());
                    contactoUnico.setApellidoContacto(formUser.getContactoApellido());
                    contactoUnico.setEmailContacto(formUser.getContactoEmail());
                    contactoUnico.setTelefonoContacto(formUser.getContactoTelefono());

                    if(formUser.getContactoNotificacionSms() != "false") {
                        contactoUnico.getFormasDeNotificacion().add(new NotificadorSms());
                    }

                    if(formUser.getContactoNotificacionEmail() != "false") {
                        contactoUnico.getFormasDeNotificacion().add(new NotificadorEmail());
                    }

                    if(formUser.getContactoNotificacionWpp() != "false") {
                        contactoUnico.getFormasDeNotificacion().add(new NotificadorWhatsapp());
                    }

                    nuevoUsuario.getPersona().getContactos().add(contactoUnico);
                    repositorioContactos.agregar(contactoUnico);
                    repositorioPersonas.agregar(nuevoUsuario.getPersona());

                    this.repositorioUsuarios.guardarUsuario(nuevoUsuario, password);

                    System.out.println("Se ha creado el usuario de forma satisfactoria!!");
                    response.status(200);
                    return new Mensaje("El usuario fue creado satisfactoriamente.").transformar();
                }
                else {
                    System.out.println("Las contraseñas son distintas.");
                    response.status(400);
                    return new Mensaje("Las contraseñas deben coincidir.").transformar();
                }
            }
            else {
                PasswordStatus passwordStatus = PasswordStatus.getInstance();
                // Todo: tirar mensajes de acuerdo a los errores que comete dicha contraseña
                List<String> lista = validador.verificarPassword(nombreUsuario, password);
                List<String> listaFiltrada = lista.stream().filter(s -> !s.equals(passwordStatus.getStatusOK())).collect(Collectors.toList());
                String fallas = new String();
                fallas = "La contraseña no cumple con los siguientes parámetros: \n";

                for(String error : listaFiltrada) {
                    fallas = fallas + " • " + error + "\n";
                }
                System.out.println(fallas);

                response.status(400);
                return new Mensaje(fallas).transformar();
            }
        }
    }

    public String editarPerfil(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("editar-perfil");
        Map<String, Object> viewModel = new HashMap<>();

        System.out.println("Recibido: " + request.body());

        String idSesion = request.headers("Authorization");
        System.out.println("ID Sesion: " + idSesion);

        try {
            Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
            Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
            System.out.println("Login: " + sesionUsuario);

            Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());
            Persona datosUsuario = repositorioPersonas.buscar(usuario.getPersona().getId());

            response.status(200);
            System.out.println(new Gson().toJson(datosUsuario));
            //return new Gson().toJson(datosUsuario);

            viewModel.put("usuario", datosUsuario);
            return template.apply(viewModel);
        }
        catch (Exception e) {
            response.status(404);
            return new Mensaje("No tiene permisos para acceder a esta zona.").transformar();
        }
    }

    public Response editarPerfilPost(Request request, Response response) {

        return response;
    }

    public String mascotasRegistradas(Request request, Response response) throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("mascotas-registradas");
        Map<String, Object> viewModel = new HashMap<>();

        // Todo: habria que obtener el ID Sesion, tal como se tendria que hacer en Editar-Perfil,
        //      buscar el usuario y de ahi obtener todas las mascotas que tenga registradas

        //viewModel.put("mascotasRegistradas", mascotas);
        return template.apply(viewModel);
    }

    public Response eliminar(Request request, Response response){
        Usuario usuario = this.repositorioUsuarios.buscar(new Integer(request.params("id")));
        this.repositorioUsuarios.eliminar(usuario);
        response.redirect("/");
        return response;
    }

}
