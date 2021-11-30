package domain.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.gson.Gson;
import domain.business.mascota.Chapa;
import domain.business.mascota.Mascota;
import domain.business.notificaciones.NotificadorEmail;
import domain.business.notificaciones.NotificadorSms;
import domain.business.notificaciones.NotificadorWhatsapp;
import domain.business.notificaciones.TipoNotificacion;
import domain.business.ubicacion.Domicilio;
import domain.business.users.Contacto;
import domain.business.users.Persona;
import domain.business.users.TipoDoc;
import domain.repositorios.*;
import domain.repositorios.factories.*;
import domain.security.TipoRol;
import domain.security.User;
import domain.security.Usuario;
import domain.security.password.PasswordStatus;
import domain.security.password.ValidadorPassword;
import json.*;
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
    private RepositorioChapas repositorioChapas = FactoryRepositorioChapas.get();
    private RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();

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
                    nuevoUsuario.getPersona().setDomicilio(new Domicilio());

                    if(formUser.getNotificacionSms() == "true") {
                        nuevoUsuario.getPersona().getFormasDeNotificacion().add(new NotificadorSms());
                    }

                    if(formUser.getNotificacionEmail() == "true") {
                        nuevoUsuario.getPersona().getFormasDeNotificacion().add(new NotificadorEmail());
                    }

                    if(formUser.getNotificacionWpp() == "true") {
                        nuevoUsuario.getPersona().getFormasDeNotificacion().add(new NotificadorWhatsapp());
                    }

                    if(formUser.getContactoNombre() != "" &&
                            formUser.getContactoApellido() != "" &&
                            formUser.getContactoEmail() != "" &&
                            formUser.getContactoTelefono() != ""){
                        Contacto contactoUnico = new Contacto();
                        contactoUnico.setNombreContacto(formUser.getContactoNombre());
                        contactoUnico.setApellidoContacto(formUser.getContactoApellido());
                        contactoUnico.setEmailContacto(formUser.getContactoEmail());
                        contactoUnico.setTelefonoContacto(formUser.getContactoTelefono());

                        if(formUser.getContactoNotificacionSms() == "true") {
                            contactoUnico.getFormasDeNotificacion().add(new NotificadorSms());
                        }

                        if(formUser.getContactoNotificacionEmail() == "true") {
                            contactoUnico.getFormasDeNotificacion().add(new NotificadorEmail());
                        }

                        if(formUser.getContactoNotificacionWpp() == "true") {
                            contactoUnico.getFormasDeNotificacion().add(new NotificadorWhatsapp());
                        }
                        nuevoUsuario.getPersona().setContactos(new ArrayList<>());
                        nuevoUsuario.getPersona().getContactos().add(contactoUnico);
                        repositorioContactos.agregar(contactoUnico);
                    }

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

    public Response eliminar(Request request, Response response){
        Usuario usuario = this.repositorioUsuarios.buscar(new Integer(request.params("id")));
        this.repositorioUsuarios.eliminar(usuario);
        response.redirect("/");
        return response;
    }

}
