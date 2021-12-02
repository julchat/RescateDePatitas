package domain.controllers;

import com.google.gson.Gson;
import domain.business.Sistema;
import domain.business.caracteristicas.Caracteristica;
import domain.business.mascota.*;
import domain.business.notificaciones.Notificador;
import domain.business.notificaciones.NotificadorEmail;
import domain.business.notificaciones.NotificadorSms;
import domain.business.notificaciones.NotificadorWhatsapp;
import domain.business.organizaciones.HogarDeTransito;
import domain.business.publicaciones.Pregunta;
import domain.business.publicaciones.PublicacionMascotaEnAdopcion;
import domain.business.publicaciones.PublicacionMascotaPerdida;
import domain.business.publicaciones.Respuesta;
import domain.business.ubicacion.Domicilio;
import domain.business.ubicacion.Lugar;
import domain.business.users.*;
import domain.repositorios.*;
import domain.repositorios.factories.*;
import domain.security.Admin;
import domain.security.TipoRol;
import domain.security.User;
import domain.security.Usuario;
import domain.security.password.PasswordStatus;
import domain.security.password.ValidadorPassword;
import json.*;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FormularioController {
    private RepositorioUsuarios repositorioUsuarios = FactoryRepositorioUsuarios.get();
    private RepositorioPersonas repositorioPersonas = FactoryRepositorioPersonas.get();
    private RepositorioContactos repositorioContactos = FactoryRepositorioContacto.get();
    private RepositorioDomicilios repositorioDomicilios = FactoryRepositorioDomicilios.get();
    private RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();
    private RepositorioChapas repositorioChapas = FactoryRepositorioChapas.get();
    private RepositorioMascotaPerdida repositorioMascotaPerdida = FactoryRepositorioMascotaPerdida.get();
    private RepositorioCaracteristicas repositorioCaracteristicas = FactoryRepositorioCaracteristicas.get();

    private RepositorioPubliMascotaPerdida repositorioPubliMascotaPerdida = FactoryRepositorioPubliMascotaPerdida.get();
    private RepositorioPubliMascotaEnAdopcion repositorioPubliMascotaEnAdopcion = FactoryRepositorioPubliMascotaEnAdopcion.get();

/* ==============================================================================================================
    Registro de Mascota -> El Dueño/Usuario registra a su Mascota
   ============================================================================================================== */
    public String registrarMascota(Request request, Response response) throws IOException {

        String idSesion = request.headers("Authorization");
        System.out.println("ID SESION: " + idSesion);

        if(idSesion == null) {
            System.out.println("No se ha iniciado sesion.");
            FormRegisterPet formulario = new Gson().fromJson(request.body(), FormRegisterPet.class);
            System.out.println(request.body());

            Persona duenio = new Persona();
            duenio.setNombre(formulario.getNombre());
            duenio.setApellido(formulario.getApellido());
            LocalDate fechaDeNacimiento = LocalDate.parse(formulario.getFechaDeNacimiento());
            duenio.setFechaDeNacimiento(fechaDeNacimiento);
            duenio.setTipoDocumento(TipoDoc.valueOf(formulario.getTipoDoc()));
            duenio.setNumeroDocumento(new Integer(formulario.getNroDocumento()));
            duenio.setEmail(formulario.getEmail());
            duenio.setTelefono(formulario.getTelefono());

            if(formulario.getProvincia() != ""
                    && formulario.getLocalidad() != ""
                    && formulario.getCodigoPostal() != ""
                    && formulario.getCalle() != ""
                    && formulario.getNumeracion() != ""
                    && formulario.getPiso() != ""
                    && formulario.getDepartamento() != "") {
                duenio.setDomicilio(new Domicilio());
                duenio.getDomicilio().setProvincia(formulario.getProvincia());
                duenio.getDomicilio().setLocalidad(formulario.getLocalidad());
                duenio.getDomicilio().setCodigoPostal(new Integer(formulario.getCodigoPostal()));
                duenio.getDomicilio().setCalle(formulario.getCalle());
                duenio.getDomicilio().setNumeracion(new Integer(formulario.getNumeracion()));
                duenio.getDomicilio().setNumeracion(new Integer(formulario.getNumeracion()));
                duenio.getDomicilio().setDepartamento(new Integer(formulario.getDepartamento()));
                repositorioDomicilios.agregar(duenio.getDomicilio());
            }

            if(formulario.getNotificacionSms() == "true") {
                duenio.getFormasDeNotificacion().add(new NotificadorSms());
            }

            if(formulario.getNotificacionEmail() == "true") {
                duenio.getFormasDeNotificacion().add(new NotificadorEmail());
            }

            if(formulario.getNotificacionWpp() == "true") {
                duenio.getFormasDeNotificacion().add(new NotificadorWhatsapp());
            }

            if(formulario.getContactoNombre() != "" &&
                formulario.getContactoApellido() != "" &&
                formulario.getContactoEmail() != "" &&
                formulario.getContactoTelefono() != ""){
                System.out.println("Se creará un nuevo contacto.");
                Contacto contactoUnico = new Contacto();
                contactoUnico.setNombreContacto(formulario.getContactoNombre());
                contactoUnico.setApellidoContacto(formulario.getContactoApellido());
                contactoUnico.setEmailContacto(formulario.getContactoEmail());
                contactoUnico.setTelefonoContacto(formulario.getContactoTelefono());

                if(formulario.getContactoNotificacionSms() == "true") {
                    contactoUnico.getFormasDeNotificacion().add(new NotificadorSms());
                }

                if(formulario.getContactoNotificacionEmail() == "true") {
                    contactoUnico.getFormasDeNotificacion().add(new NotificadorEmail());
                }

                if(formulario.getContactoNotificacionWpp() == "true") {
                    contactoUnico.getFormasDeNotificacion().add(new NotificadorWhatsapp());
                }

                duenio.setContactos(new ArrayList<>());
                duenio.getContactos().add(contactoUnico);
                repositorioContactos.agregar(contactoUnico);
            }

            Mascota nuevaMascota = new Mascota();
            nuevaMascota.setNombreMascota(formulario.getNombreMascota());
            nuevaMascota.setApodoMascota(formulario.getApodoMascota());
            nuevaMascota.setEdadMascota(new Integer(formulario.getEdadMascota()));
            nuevaMascota.setTipoAnimal(TipoAnimal.valueOf(formulario.getTipoAnimal()));
            nuevaMascota.setSexoMascota(SexoMascota.valueOf(formulario.getSexoMascota()));

            if(formulario.getCaracteristicasElegidas().size() > 0) {
                for(String idCaracteristica : formulario.getCaracteristicasElegidas()) {
                    Caracteristica caracteristicaElegida = repositorioCaracteristicas.buscar(new Integer(idCaracteristica));
                    nuevaMascota.getCaracteristicasMascota().add(caracteristicaElegida);
                }
            }
            //Todo: faltan las Fotos
            nuevaMascota.setDescripcionMascota(formulario.getDescripcionMascota());

            Chapa nuevaChapita = new Chapa(duenio, nuevaMascota);
            System.out.println("ID CHAPA: " + nuevaChapita.getId());
            repositorioChapas.agregar(nuevaChapita);

            System.out.println("ID CHAPA: " + nuevaChapita.getId());
            nuevaChapita.generarQR(nuevaChapita.getId());

            repositorioMascotas.agregar(nuevaMascota);
            repositorioPersonas.agregar(duenio);


            if(formulario.getRegister().equals("si")) {
                String nombreUsuario = formulario.getUserName();
                String password = formulario.getPassword();
                String passwordConfirm = formulario.getPassConf();

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

                            nuevoUsuario.setPersona((Persona) duenio);

                            repositorioUsuarios.guardarUsuario(nuevoUsuario, password);

                            System.out.println("Se ha creado el usuario de forma satisfactoria.");
                            response.status(200);
                            return new Mensaje("El usuario y la mascota fueron registrados satisfactoriamente.").transformar();
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
            else {
                System.out.println("Se ha registrado la mascota de forma satisfactoria.");
                return new Mensaje("Se ha registrado la mascota de forma satisfactoria.").transformar();
            }
        }

        // Registro de Mascota luego de haber iniciado Sesion
        else {
            System.out.println("Se inicio sesion.");
            Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
            Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
            System.out.println("Login: " + sesionUsuario.getNombreUsuario());

            FormRegisterPet formulario = new Gson().fromJson(request.body(), FormRegisterPet.class);
            System.out.println(request.body());

            Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());
            Persona duenio = repositorioPersonas.buscar(usuario.getPersona().getId());
            System.out.println(new Gson().toJson(duenio));

            if(duenio.getDomicilio().getLocalidad() == null
                    && duenio.getDomicilio().getProvincia() == null
                    && duenio.getDomicilio().getCalle() == null) {
                if(formulario.getProvincia() != ""
                        && formulario.getLocalidad() != ""
                        && formulario.getCodigoPostal() != ""
                        && formulario.getCalle() != ""
                        && formulario.getNumeracion() != ""
                        && formulario.getPiso() != ""
                        && formulario.getDepartamento() != "") {
                    duenio.getDomicilio().setProvincia(formulario.getProvincia());
                    duenio.getDomicilio().setLocalidad(formulario.getLocalidad());
                    duenio.getDomicilio().setCodigoPostal(new Integer(formulario.getCodigoPostal()));
                    duenio.getDomicilio().setCalle(formulario.getCalle());
                    duenio.getDomicilio().setNumeracion(new Integer(formulario.getNumeracion()));
                    duenio.getDomicilio().setNumeracion(new Integer(formulario.getNumeracion()));
                    duenio.getDomicilio().setDepartamento(new Integer(formulario.getDepartamento()));
                }
                repositorioDomicilios.modificar(duenio.getDomicilio());
            }

            Mascota nuevaMascota = new Mascota();
            nuevaMascota.setNombreMascota(formulario.getNombreMascota());
            nuevaMascota.setApodoMascota(formulario.getApodoMascota());
            nuevaMascota.setEdadMascota(new Integer(formulario.getEdadMascota()));
            nuevaMascota.setTipoAnimal(TipoAnimal.valueOf(formulario.getTipoAnimal()));
            nuevaMascota.setSexoMascota(SexoMascota.valueOf(formulario.getSexoMascota()));

            if(formulario.getCaracteristicasElegidas().size() > 0) {
                for(String idCaracteristica : formulario.getCaracteristicasElegidas()) {
                    Caracteristica caracteristicaElegida = repositorioCaracteristicas.buscar(new Integer(idCaracteristica));
                    nuevaMascota.getCaracteristicasMascota().add(caracteristicaElegida);
                }
            }
            //Todo: faltan agregar las Fotos
            nuevaMascota.setDescripcionMascota(formulario.getDescripcionMascota());

            Chapa nuevaChapita = new Chapa(duenio, nuevaMascota);
            System.out.println("ID CHAPA: " + nuevaChapita.getId());
            repositorioChapas.agregar(nuevaChapita);

            System.out.println("ID CHAPA: " + nuevaChapita.getId());
            nuevaChapita.generarQR(nuevaChapita.getId());

            repositorioMascotas.agregar(nuevaMascota);
            repositorioPersonas.modificar(duenio);

            response.status(200);
            return new Mensaje("Se ha registrado la mascota de forma satisfactoria.").transformar();
        }
    }


/* ==============================================================================================================
    Dar Mascota en Adopción -> El Dueño completa el formulario para dar en adopción a una de sus mascotas
   ============================================================================================================== */

    public String darMascotaAdopcion(Request request, Response response) throws IOException {

        String idSesion = request.headers("Authorization");
        System.out.println("ID SESION: " + idSesion);

        if(idSesion == null) {
            System.out.println("No se ha iniciado sesion.");
            FormRegisterPet formulario = new Gson().fromJson(request.body(), FormRegisterPet.class);
            System.out.println(request.body());

            Persona duenio = new Persona();
            duenio.setNombre(formulario.getNombre());
            duenio.setApellido(formulario.getApellido());
            LocalDate fechaDeNacimiento = LocalDate.parse(formulario.getFechaDeNacimiento());
            duenio.setFechaDeNacimiento(fechaDeNacimiento);
            duenio.setTipoDocumento(TipoDoc.valueOf(formulario.getTipoDoc()));
            duenio.setNumeroDocumento(new Integer(formulario.getNroDocumento()));
            duenio.setEmail(formulario.getEmail());
            duenio.setTelefono(formulario.getTelefono());

            if(formulario.getProvincia() != ""
                    && formulario.getLocalidad() != ""
                    && formulario.getCodigoPostal() != ""
                    && formulario.getCalle() != ""
                    && formulario.getNumeracion() != ""
                    && formulario.getPiso() != ""
                    && formulario.getDepartamento() != "") {
                duenio.setDomicilio(new Domicilio());
                duenio.getDomicilio().setProvincia(formulario.getProvincia());
                duenio.getDomicilio().setLocalidad(formulario.getLocalidad());
                duenio.getDomicilio().setCodigoPostal(new Integer(formulario.getCodigoPostal()));
                duenio.getDomicilio().setCalle(formulario.getCalle());
                duenio.getDomicilio().setNumeracion(new Integer(formulario.getNumeracion()));
                duenio.getDomicilio().setNumeracion(new Integer(formulario.getNumeracion()));
                duenio.getDomicilio().setDepartamento(new Integer(formulario.getDepartamento()));
                repositorioDomicilios.agregar(duenio.getDomicilio());
            }

            if(formulario.getNotificacionSms() == "true") {
                duenio.getFormasDeNotificacion().add(new NotificadorSms());
            }

            if(formulario.getNotificacionEmail() == "true") {
                duenio.getFormasDeNotificacion().add(new NotificadorEmail());
            }

            if(formulario.getNotificacionWpp() == "true") {
                duenio.getFormasDeNotificacion().add(new NotificadorWhatsapp());
            }

            if(formulario.getContactoNombre() != "" &&
                    formulario.getContactoApellido() != "" &&
                    formulario.getContactoEmail() != "" &&
                    formulario.getContactoTelefono() != ""){
                System.out.println("Se creará un nuevo contacto.");
                Contacto contactoUnico = new Contacto();
                contactoUnico.setNombreContacto(formulario.getContactoNombre());
                contactoUnico.setApellidoContacto(formulario.getContactoApellido());
                contactoUnico.setEmailContacto(formulario.getContactoEmail());
                contactoUnico.setTelefonoContacto(formulario.getContactoTelefono());

                if(formulario.getContactoNotificacionSms() == "true") {
                    contactoUnico.getFormasDeNotificacion().add(new NotificadorSms());
                }

                if(formulario.getContactoNotificacionEmail() == "true") {
                    contactoUnico.getFormasDeNotificacion().add(new NotificadorEmail());
                }

                if(formulario.getContactoNotificacionWpp() == "true") {
                    contactoUnico.getFormasDeNotificacion().add(new NotificadorWhatsapp());
                }

                duenio.setContactos(new ArrayList<>());
                duenio.getContactos().add(contactoUnico);
                repositorioContactos.agregar(contactoUnico);
            }

            Mascota mascotaElegida = new Mascota();
            mascotaElegida.setNombreMascota(formulario.getNombreMascota());
            mascotaElegida.setApodoMascota(formulario.getApodoMascota());
            mascotaElegida.setEdadMascota(new Integer(formulario.getEdadMascota()));
            mascotaElegida.setTipoAnimal(TipoAnimal.valueOf(formulario.getTipoAnimal()));
            mascotaElegida.setSexoMascota(SexoMascota.valueOf(formulario.getSexoMascota()));

            if(formulario.getCaracteristicasElegidas().size() > 0) {
                for(String idCaracteristica : formulario.getCaracteristicasElegidas()) {
                    Caracteristica caracteristicaElegida = repositorioCaracteristicas.buscar(new Integer(idCaracteristica));
                    mascotaElegida.getCaracteristicasMascota().add(caracteristicaElegida);
                }
            }
            //Todo: faltan las Fotos
            mascotaElegida.setDescripcionMascota(formulario.getDescripcionMascota());

            Chapa nuevaChapita = new Chapa(duenio, mascotaElegida);
            System.out.println("ID CHAPA: " + nuevaChapita.getId());
            repositorioChapas.agregar(nuevaChapita);

            System.out.println("ID CHAPA: " + nuevaChapita.getId());
            nuevaChapita.generarQR(nuevaChapita.getId());

            repositorioMascotas.agregar(mascotaElegida);
            repositorioPersonas.agregar(duenio);

            RepositorioPreguntas repositorioPreguntas = FactoryRepositorioPreguntas.get();
            List<Pregunta> preguntas = repositorioPreguntas.buscarTodos();
            List<Respuesta> respuestasPublicacion = new ArrayList<>();
            int contador = 0;
            for(Pregunta pregunta : preguntas) {
                System.out.println(pregunta.getPregunta());

                Respuesta nuevaRespuesta = new Respuesta();
                nuevaRespuesta.setPregunta(pregunta);
                System.out.println(formulario.getRespuestas().get(contador));

                nuevaRespuesta.setRespuesta(formulario.getRespuestas().get(contador));
                respuestasPublicacion.add(nuevaRespuesta);
                contador ++;
            }
            System.out.println(JsonController.transformar(respuestasPublicacion));

            PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion = new PublicacionMascotaEnAdopcion();
            publicacionMascotaEnAdopcion.crearPublicacion(duenio, mascotaElegida, respuestasPublicacion);
            repositorioPubliMascotaEnAdopcion.agregar(publicacionMascotaEnAdopcion);


            response.status(200);
            return new Mensaje("Se ha creado la publicación para dar en adopción a la mascota elegida de forma satisfactoria.").transformar();
        }

        // Registro de Mascota luego de haber iniciado Sesion
        else {
            System.out.println("Se inicio sesion.");
            Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
            Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
            System.out.println("Login: " + sesionUsuario.getNombreUsuario());

            FormRegisterPet formulario = new Gson().fromJson(request.body(), FormRegisterPet.class);
            System.out.println(request.body());

            Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());
            Persona duenio = repositorioPersonas.buscar(usuario.getPersona().getId());
            System.out.println(new Gson().toJson(duenio));

            if(duenio.getDomicilio().getLocalidad() == null
                    && duenio.getDomicilio().getProvincia() == null
                    && duenio.getDomicilio().getCalle() == null) {
                if(formulario.getProvincia() != ""
                        && formulario.getLocalidad() != ""
                        && formulario.getCodigoPostal() != ""
                        && formulario.getCalle() != ""
                        && formulario.getNumeracion() != ""
                        && formulario.getPiso() != ""
                        && formulario.getDepartamento() != "") {
                    duenio.getDomicilio().setProvincia(formulario.getProvincia());
                    duenio.getDomicilio().setLocalidad(formulario.getLocalidad());
                    duenio.getDomicilio().setCodigoPostal(new Integer(formulario.getCodigoPostal()));
                    duenio.getDomicilio().setCalle(formulario.getCalle());
                    duenio.getDomicilio().setNumeracion(new Integer(formulario.getNumeracion()));
                    duenio.getDomicilio().setNumeracion(new Integer(formulario.getNumeracion()));
                    duenio.getDomicilio().setDepartamento(new Integer(formulario.getDepartamento()));
                }
                repositorioDomicilios.modificar(duenio.getDomicilio());
            }

            Mascota mascotaElegida = new Mascota();
            mascotaElegida.setNombreMascota(formulario.getNombreMascota());
            mascotaElegida.setApodoMascota(formulario.getApodoMascota());
            mascotaElegida.setEdadMascota(new Integer(formulario.getEdadMascota()));
            mascotaElegida.setTipoAnimal(TipoAnimal.valueOf(formulario.getTipoAnimal()));
            mascotaElegida.setSexoMascota(SexoMascota.valueOf(formulario.getSexoMascota()));

            if(formulario.getCaracteristicasElegidas().size() > 0) {
                for(String idCaracteristica : formulario.getCaracteristicasElegidas()) {
                    Caracteristica caracteristicaElegida = repositorioCaracteristicas.buscar(new Integer(idCaracteristica));
                    mascotaElegida.getCaracteristicasMascota().add(caracteristicaElegida);
                }
            }
            //Todo: faltan agregar las Fotos
            mascotaElegida.setDescripcionMascota(formulario.getDescripcionMascota());

            Chapa nuevaChapita = new Chapa(duenio, mascotaElegida);
            System.out.println("ID CHAPA: " + nuevaChapita.getId());
            repositorioChapas.agregar(nuevaChapita);

            System.out.println("ID CHAPA: " + nuevaChapita.getId());
            nuevaChapita.generarQR(nuevaChapita.getId());

            repositorioMascotas.agregar(mascotaElegida);
            repositorioPersonas.modificar(duenio);


            RepositorioPreguntas repositorioPreguntas = FactoryRepositorioPreguntas.get();
            List<Pregunta> preguntas = repositorioPreguntas.buscarTodos();
            List<Respuesta> respuestasPublicacion = new ArrayList<>();
            int contador = 0;
            for(Pregunta pregunta : preguntas) {
                System.out.println(pregunta.getPregunta());

                Respuesta nuevaRespuesta = new Respuesta();
                nuevaRespuesta.setPregunta(pregunta);
                System.out.println(formulario.getRespuestas().get(contador));

                nuevaRespuesta.setRespuesta(formulario.getRespuestas().get(contador));
                respuestasPublicacion.add(nuevaRespuesta);
                contador ++;
            }
            System.out.println(JsonController.transformar(respuestasPublicacion));

            PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion = new PublicacionMascotaEnAdopcion();
            publicacionMascotaEnAdopcion.crearPublicacion(duenio, mascotaElegida, respuestasPublicacion);
            repositorioPubliMascotaEnAdopcion.agregar(publicacionMascotaEnAdopcion);


            response.status(200);
            return new Mensaje("Se ha creado la publicación para dar en adopción a la mascota elegida de forma satisfactoria.").transformar();
        }
    }

    public String darMascotaAdopcionParticular(Request request, Response response) {
        String idSesion = request.headers("Authorization");
        System.out.println("ID SESION: " + idSesion);

        int idMascota = new Integer(request.params("id"));

        Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
        Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
        System.out.println("Login: " + sesionUsuario.getNombreUsuario());

        FormRegisterPet formulario = new Gson().fromJson(request.body(), FormRegisterPet.class);
        System.out.println(request.body());

        Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());
        Persona duenio = repositorioPersonas.buscar(usuario.getPersona().getId());
        System.out.println(new Gson().toJson(duenio));

        Mascota mascotaElegida = repositorioMascotas.buscar(idMascota);
        System.out.println(new Gson().toJson(mascotaElegida));

        RepositorioPreguntas repositorioPreguntas = FactoryRepositorioPreguntas.get();
        List<Pregunta> preguntas = repositorioPreguntas.buscarTodos();
        List<Respuesta> respuestasPublicacion = new ArrayList<>();
        int contador = 0;
        for(Pregunta pregunta : preguntas) {
            System.out.println(pregunta.getPregunta());

            Respuesta nuevaRespuesta = new Respuesta();
            nuevaRespuesta.setPregunta(pregunta);
            System.out.println(formulario.getRespuestas().get(contador));

            nuevaRespuesta.setRespuesta(formulario.getRespuestas().get(contador));
            respuestasPublicacion.add(nuevaRespuesta);
            contador ++;
        }
        System.out.println(JsonController.transformar(respuestasPublicacion));

        PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion = new PublicacionMascotaEnAdopcion();
        publicacionMascotaEnAdopcion.crearPublicacion(duenio, mascotaElegida, respuestasPublicacion);
        repositorioPubliMascotaEnAdopcion.agregar(publicacionMascotaEnAdopcion);

        response.status(200);
        return new Mensaje("Se ha creado la publicación para dar en adopción a la mascota elegida de forma satisfactoria.").transformar();
    }

/* ==============================================================================================================
    Adoptar a una Mascota -> El usuario se comunica con el Dueño de la mascota que desea adoptar
   ============================================================================================================== */

    public String notificarDuenio(Request request, Response response) {
        int idPublicacion = new Integer(request.params("id"));

        Persona persona = new Persona();
        persona.setNombre(request.queryParams("nombre"));
        persona.setApellido(request.queryParams("apellido"));
        persona.setTipoDocumento(TipoDoc.valueOf(request.queryParams("tipoDoc")));
        persona.setNumeroDocumento(new Integer(request.queryParams("nroDocumento")));
        LocalDate fechaDeNacimiento = LocalDate.parse(request.queryParams("fechaDeNacimiento"));
        persona.setFechaDeNacimiento(fechaDeNacimiento);
        persona.setTelefono(request.queryParams("telefono"));
        persona.setEmail(request.queryParams("email"));

        if(request.queryParams("SMS") == "true") {
            persona.getFormasDeNotificacion().add(new NotificadorSms());
        }

        if(request.queryParams("EMAIL") == "true") {
            persona.getFormasDeNotificacion().add(new NotificadorEmail());
        }

        if(request.queryParams("WHATSAPP") == "true") {
            persona.getFormasDeNotificacion().add(new NotificadorWhatsapp());
        }

        PublicacionMascotaEnAdopcion publicacion = repositorioPubliMascotaEnAdopcion.buscar(idPublicacion);

        Persona duenio = repositorioPersonas.buscar(publicacion.getAutor().getId());
        Mascota mascotaElegida = repositorioMascotas.buscar(publicacion.getMascotaElegida().getId());

        //Todo: comentado porque utiliza Twilio
        //Notificador.getInstance().notificarPorAdopcion(duenio, persona, mascotaElegida);
        //Todo: no se está tomando en cuenta a los interesados en la publicación, directamente si uno esta interesado/a
        //      entonces se comunica o le envía una notificación al dueño de la mascota (autor de la publicación)
        response.status(200);
        return new Mensaje("El formulario se ha enviado correctamente. El dueño de la mascota se comunicará con usted pronto.").transformar();
}


/* ==============================================================================================================
    Buscar una Mascota Ideal -> El usuario ingresa sus preferencias y comodidades para que le sugieran una mascota en adopción
   ============================================================================================================== */

    public String buscarMascotaIdeal(Request request, Response response)  {

        return null;
    }


/* ==============================================================================================================
    Reportar Mascota Perdida (con Chapita) -> Escaneando el Código QR se llega a este Formulario
   ============================================================================================================== */

    public String mascotaPerdidaChapita(Request request, Response response) {

        int idChapita = new Integer(request.params("id"));
        System.out.println("ID CHAPA: " + idChapita);
        RepositorioChapas repositorioChapas = FactoryRepositorioChapas.get();
        Chapa chapita = repositorioChapas.buscarChapa(idChapita);

        FormUser formUser = new Gson().fromJson(request.body(), FormUser.class);
        System.out.println(request.body());

        Persona rescatista = new Persona();
        rescatista.setNombre(formUser.getNombre());
        rescatista.setApellido(formUser.getApellido());
        rescatista.setFechaDeNacimiento(LocalDate.parse(formUser.getFechaDeNacimiento()));
        rescatista.setTipoDocumento(TipoDoc.valueOf(formUser.getTipoDoc()));
        rescatista.setNumeroDocumento(new Integer(formUser.getNroDocumento()));
        rescatista.setEmail(formUser.getEmail());
        rescatista.setTelefono(formUser.getTelefono());

        if(formUser.getNotificacionSms() == "true") {
            rescatista.getFormasDeNotificacion().add(new NotificadorSms());
        }

        if(formUser.getNotificacionEmail() == "true") {
            rescatista.getFormasDeNotificacion().add(new NotificadorEmail());
        }

        if(formUser.getNotificacionWpp() == "true") {
            rescatista.getFormasDeNotificacion().add(new NotificadorWhatsapp());
        }

        if(formUser.getContactoNombre() != "" &&
                formUser.getContactoApellido()!= "" &&
                formUser.getContactoEmail() != "" &&
                formUser.getContactoTelefono() != ""){
            System.out.println("Se creará un nuevo contacto.");
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
            rescatista.setContactos(new ArrayList<>());
            rescatista.getContactos().add(contactoUnico);
            repositorioContactos.agregar(contactoUnico);
        }

        rescatista.setDomicilio(new Domicilio());
        rescatista.getDomicilio().setProvincia(formUser.getProvincia());
        rescatista.getDomicilio().setLocalidad(formUser.getLocalidad());
        rescatista.getDomicilio().setCodigoPostal(new Integer(formUser.getCodigoPostal()));
        rescatista.getDomicilio().setCalle(formUser.getCalle());
        rescatista.getDomicilio().setNumeracion(new Integer(formUser.getNumeracion()));
        rescatista.getDomicilio().setDepartamento(new Integer(formUser.getDepartamento()));
        rescatista.getDomicilio().setPiso(new Integer(formUser.getPiso()));

        repositorioDomicilios.agregar(rescatista.getDomicilio());
        repositorioPersonas.agregar(rescatista);

        chapita.notificarDuenio(rescatista);

        response.status(200);
        return new Mensaje("El formulario se ha enviado correctamente. El dueño de la mascota se comunicará con usted pronto.").transformar();
    }


/* ==============================================================================================================
    Reportar Mascota Perdida (sin Chapita) -> El Rescatista tiene que rellenar este Formulario y crea una Publicacion
   ============================================================================================================== */

    public String mascotaPerdida(Request request, Response response) {
        Sistema miSistema = Sistema.getInstance();

        String idSesion = request.headers("Authorization");
        System.out.println("ID SESION: " + idSesion);

        if(idSesion == null) {
            System.out.println("No se ha iniciado sesion.");
            FormFoundPet formulario = new Gson().fromJson(request.body(), FormFoundPet.class);
            System.out.println(request.body());

            Persona rescatista = new Persona();
            rescatista.setNombre(formulario.getNombre());
            rescatista.setApellido(formulario.getApellido());
            LocalDate fechaDeNacimiento = LocalDate.parse(formulario.getFechaDeNacimiento());
            rescatista.setFechaDeNacimiento(fechaDeNacimiento);
            rescatista.setTipoDocumento(TipoDoc.valueOf(formulario.getTipoDoc()));
            rescatista.setNumeroDocumento(new Integer(formulario.getNroDocumento()));
            rescatista.setEmail(formulario.getEmail());
            rescatista.setTelefono(formulario.getTelefono());

            if(formulario.getProvincia() != ""
                    && formulario.getLocalidad() != ""
                    && formulario.getCodigoPostal() != ""
                    && formulario.getCalle() != ""
                    && formulario.getNumeracion() != ""
                    && formulario.getPiso() != ""
                    && formulario.getDepartamento() != "") {
                rescatista.setDomicilio(new Domicilio());
                rescatista.getDomicilio().setProvincia(formulario.getProvincia());
                rescatista.getDomicilio().setLocalidad(formulario.getLocalidad());
                rescatista.getDomicilio().setCodigoPostal(new Integer(formulario.getCodigoPostal()));
                rescatista.getDomicilio().setCalle(formulario.getCalle());
                rescatista.getDomicilio().setNumeracion(new Integer(formulario.getNumeracion()));
                rescatista.getDomicilio().setPiso(new Integer(formulario.getPiso()));
                rescatista.getDomicilio().setDepartamento(new Integer(formulario.getDepartamento()));
                repositorioDomicilios.agregar(rescatista.getDomicilio());
            }

            if(formulario.getNotificacionSms() == "true") {
                rescatista.getFormasDeNotificacion().add(new NotificadorSms());
            }

            if(formulario.getNotificacionEmail() == "true") {
                rescatista.getFormasDeNotificacion().add(new NotificadorEmail());
            }

            if(formulario.getNotificacionWpp() == "true") {
                rescatista.getFormasDeNotificacion().add(new NotificadorWhatsapp());
            }

            if(formulario.getContactoNombre().equals("") &&
                    formulario.getContactoApellido().equals("") &&
                    formulario.getContactoEmail().equals("") &&
                    formulario.getContactoTelefono().equals("")){
                System.out.println("Se creará un nuevo contacto.");
                Contacto contactoUnico = new Contacto();
                contactoUnico.setNombreContacto(formulario.getContactoNombre());
                contactoUnico.setApellidoContacto(formulario.getContactoApellido());
                contactoUnico.setEmailContacto(formulario.getContactoEmail());
                contactoUnico.setTelefonoContacto(formulario.getContactoTelefono());

                if(formulario.getContactoNotificacionSms() == "true") {
                    contactoUnico.getFormasDeNotificacion().add(new NotificadorSms());
                }

                if(formulario.getContactoNotificacionEmail() == "true") {
                    contactoUnico.getFormasDeNotificacion().add(new NotificadorEmail());
                }

                if(formulario.getContactoNotificacionWpp() == "true") {
                    contactoUnico.getFormasDeNotificacion().add(new NotificadorWhatsapp());
                }

                rescatista.setContactos(new ArrayList<>());
                rescatista.getContactos().add(contactoUnico);
                repositorioContactos.agregar(contactoUnico);
            }


            System.out.println("PERSISTIENDO MASCOTA");
            MascotaPerdida mascotaPerdida = new MascotaPerdida();
            mascotaPerdida.setTamanio(Tamanio.valueOf(formulario.getTamanioMascota()));
            mascotaPerdida.setTipoAnimal(TipoAnimal.valueOf(formulario.getTipoAnimal()));
            mascotaPerdida.setSexoMascota(SexoMascota.valueOf(formulario.getSexoMascota()));
            mascotaPerdida.setDescripcion(formulario.getDescripcionMascota());
            Lugar lugarEncontrada = new Lugar();
            mascotaPerdida.setLugarEncontrada(lugarEncontrada);
            //TODO: Ubicacion y Foto

            System.out.println(JsonController.transformar(mascotaPerdida));

            repositorioPersonas.agregar(rescatista);
            repositorioMascotaPerdida.agregar(mascotaPerdida);

            // En este caso, el Rescatista se involucra y puede albergar a la mascota
            if(formulario.getTransito().equals("si")) {
                rescatista.setPuedeAlojarMascota(true);

                Lugar nuevoLugar = new Lugar();
                mascotaPerdida.setLugarDeTransito(nuevoLugar.mapearLugar(rescatista.getDomicilio()));

                rescatista.setMascotasAlojadas(new ArrayList<>());
                rescatista.getMascotasAlojadas().add(mascotaPerdida);

                repositorioMascotaPerdida.modificar(mascotaPerdida);
                repositorioPersonas.modificar(rescatista);
            }
            // En este caso, se le buscara un Hogar de Transito adecuado para albergar a la mascota
            else {
                int radioKM = new Integer(formulario.getRadioKM());


    // TODO: falta terminar lo de obtener un Hogar de Tránsito adecuado según la distancia
    //      para eso necesitamos si o si los valores de Longitud y Latitud, asi podemos ubicar y obtener el más cercano que cumpla

                HogarDeTransito hogarAdecuado = miSistema.buscarHogarMasCercano(radioKM, mascotaPerdida);
                hogarAdecuado.alojarMascota(mascotaPerdida);
            }

            PublicacionMascotaPerdida publicacionCreada = new PublicacionMascotaPerdida();
            publicacionCreada.crearPublicacion(rescatista, mascotaPerdida);
            repositorioPubliMascotaPerdida.agregar(publicacionCreada);

            response.status(200);
            return new Mensaje("Se ha reportado a la mascota de forma satisfactoria.").transformar();
        }

        // Registro de Mascota luego de haber iniciado Sesion
        else {
            System.out.println("Se inicio sesion.");
            Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
            Usuario sesionUsuario = (Usuario) atributosSesion.get("usuario");
            System.out.println("Login: " + sesionUsuario.getNombreUsuario());

            FormFoundPet formulario = new Gson().fromJson(request.body(), FormFoundPet.class);
            System.out.println(request.body());

            Usuario usuario = repositorioUsuarios.buscar(sesionUsuario.getId());
            Persona rescatista = repositorioPersonas.buscar(usuario.getPersona().getId());
            System.out.println(new Gson().toJson(rescatista));

            if(rescatista.getDomicilio().getLocalidad() == null
                    && rescatista.getDomicilio().getProvincia() == null
                    && rescatista.getDomicilio().getCalle() == null) {
                if(formulario.getProvincia() != ""
                        && formulario.getLocalidad() != ""
                        && formulario.getCodigoPostal() != ""
                        && formulario.getCalle() != ""
                        && formulario.getNumeracion() != ""
                        && formulario.getPiso() != ""
                        && formulario.getDepartamento() != "") {
                    rescatista.getDomicilio().setProvincia(formulario.getProvincia());
                    rescatista.getDomicilio().setLocalidad(formulario.getLocalidad());
                    rescatista.getDomicilio().setCodigoPostal(new Integer(formulario.getCodigoPostal()));
                    rescatista.getDomicilio().setCalle(formulario.getCalle());
                    rescatista.getDomicilio().setNumeracion(new Integer(formulario.getNumeracion()));
                    rescatista.getDomicilio().setNumeracion(new Integer(formulario.getNumeracion()));
                    rescatista.getDomicilio().setDepartamento(new Integer(formulario.getDepartamento()));
                }
                repositorioDomicilios.modificar(rescatista.getDomicilio());
            }

            MascotaPerdida mascotaPerdida = new MascotaPerdida();
            mascotaPerdida.setTamanio(Tamanio.valueOf(formulario.getTamanioMascota()));
            mascotaPerdida.setTipoAnimal(TipoAnimal.valueOf(formulario.getTipoAnimal()));
            mascotaPerdida.setSexoMascota(SexoMascota.valueOf(formulario.getSexoMascota()));
            mascotaPerdida.setDescripcion(formulario.getDescripcionMascota());
            //TODO: Ubicacion y Foto
            System.out.println(JsonController.transformar(mascotaPerdida));

            repositorioMascotaPerdida.agregar(mascotaPerdida);

            // En este caso, el Rescatista se involucra y puede albergar a la mascota
            if(formulario.getTransito().equals("si")) {
                rescatista.setPuedeAlojarMascota(true);

                Lugar nuevoLugar = new Lugar();
                mascotaPerdida.setLugarDeTransito(nuevoLugar.mapearLugar(rescatista.getDomicilio()));

                rescatista.setMascotasAlojadas(new ArrayList<>());
                rescatista.getMascotasAlojadas().add(mascotaPerdida);

                repositorioMascotaPerdida.modificar(mascotaPerdida);
                repositorioPersonas.modificar(rescatista);
            }
            // En este caso, se le buscara un Hogar de Transito adecuado para albergar a la mascota
            else {
                int radioKM = new Integer(formulario.getRadioKM());

// TODO: falta terminar lo de obtener un Hogar de Tránsito adecuado según la distancia
//      para eso necesitamos si o si los valores de Longitud y Latitud, asi podemos ubicar y obtener el más cercano que cumpla

                HogarDeTransito hogarAdecuado = miSistema.buscarHogarMasCercano(radioKM, mascotaPerdida);
                hogarAdecuado.alojarMascota(mascotaPerdida);
            }

            PublicacionMascotaPerdida publicacionCreada = new PublicacionMascotaPerdida();
            publicacionCreada.crearPublicacion(rescatista, mascotaPerdida);
            repositorioPubliMascotaPerdida.agregar(publicacionCreada);

            response.status(200);
            return new Mensaje("Se ha reportado a la mascota de forma satisfactoria.").transformar();
        }
    }


/* ==============================================================================================================
    Encontrar una Mascota Perdida -> El Dueño se comunica con el Rescatista por la Mascota que encontró
   ============================================================================================================== */

    public String notificarRescatista(Request request, Response response) {
        int idPublicacion = new Integer(request.params("id"));

        Persona persona = new Persona();
        persona.setNombre(request.queryParams("nombre"));
        persona.setApellido(request.queryParams("apellido"));
        persona.setTipoDocumento(TipoDoc.valueOf(request.queryParams("tipoDoc")));
        persona.setNumeroDocumento(new Integer(request.queryParams("nroDocumento")));
        LocalDate fechaDeNacimiento = LocalDate.parse(request.queryParams("fechaDeNacimiento"));
        persona.setFechaDeNacimiento(fechaDeNacimiento);
        persona.setTelefono(request.queryParams("telefono"));
        persona.setEmail(request.queryParams("email"));

        if(request.queryParams("SMS") == "true") {
            persona.getFormasDeNotificacion().add(new NotificadorSms());
        }

        if(request.queryParams("EMAIL") == "true") {
            persona.getFormasDeNotificacion().add(new NotificadorEmail());
        }

        if(request.queryParams("WHATSAPP") == "true") {
            persona.getFormasDeNotificacion().add(new NotificadorWhatsapp());
        }

        PublicacionMascotaPerdida publicacion = repositorioPubliMascotaPerdida.buscar(idPublicacion);

        Persona rescatista = repositorioPersonas.buscar(publicacion.getAutor().getId());
        MascotaPerdida mascotaPerdida = repositorioMascotaPerdida.buscar(publicacion.getMascotaRescatada().getId());

        //Todo: comentado porque utiliza Twilio
        //Notificador.getInstance().notificarRescatista(rescatista, persona, mascotaPerdida);

        response.status(200);
        return new Mensaje("El formulario se ha enviado correctamente. El rescatista se comunicará con usted pronto.").transformar();
    }



/* ==============================================================================================================
    Administrar las Características -> El Admin puede agregar una nueva característica, o elegir cual pueden ver los demás
   ============================================================================================================== */

    public String agregarCaracteristica(Request request, Response response) {

        FormCarac caracteristicas = new Gson().fromJson(request.body(), FormCarac.class);
        System.out.println(request.body());

        if(caracteristicas.getCaracteristicaNueva() != "") {
            System.out.println("Caracteristica a agregar: " + caracteristicas.getCaracteristicaNueva());
            Caracteristica nuevaCaracteristica = new Caracteristica();
            nuevaCaracteristica.setCaracteristica(caracteristicas.getCaracteristicaNueva());
            nuevaCaracteristica.setEsVisible(true);
            repositorioCaracteristicas.agregar(nuevaCaracteristica);

            response.status(200);
            return new Mensaje("La característica fue agregada satisfactoriamente.").transformar();
        }
        else {
            response.status(204);
            return new Mensaje("No se agregó ninguna característica.").transformar();
        }
    }

    public String visibilizarCaracteristicas(Request request, Response response) {

        FormCarac caracteristicas = new Gson().fromJson(request.body(), FormCarac.class);
        System.out.println(request.body());

        if(!caracteristicas.getCaracteristicasVisibles().isEmpty()) {
            System.out.println("Caracteristicas a Mostrar: " + caracteristicas.getCaracteristicasVisibles());
            for(String idCaracteristicaAMostrar : caracteristicas.getCaracteristicasVisibles()) {
                Caracteristica caracteristicaAMostrar = repositorioCaracteristicas.buscar(new Integer(idCaracteristicaAMostrar));

                caracteristicaAMostrar.setEsVisible(true);
                repositorioCaracteristicas.modificar(caracteristicaAMostrar);
            }
            response.status(200);
            return new Mensaje("Se mostrarán las características elegidas.").transformar();
        }
        else {
            response.status(204);
            return new Mensaje("No se eligieron características para visibilizar.").transformar();
        }
    }

    public String ocultarCaracteristicas(Request request, Response response) {

        FormCarac caracteristicas = new Gson().fromJson(request.body(), FormCarac.class);
        System.out.println(request.body());

        if(!caracteristicas.getCaracteristicasAQuitar().isEmpty()) {
            System.out.println("Caracteristicas a Quitar: " + caracteristicas.getCaracteristicasAQuitar());
            for(String idCaracteristicaAQuitar : caracteristicas.getCaracteristicasAQuitar()) {
                Caracteristica caracteristicaAQuitar = repositorioCaracteristicas.buscar(new Integer(idCaracteristicaAQuitar));

                caracteristicaAQuitar.setEsVisible(false);
                repositorioCaracteristicas.modificar(caracteristicaAQuitar);
            }
            response.status(200);
            return new Mensaje("Se ocultaron las características elegidas.").transformar();
        }
        else {
            response.status(204);
            return new Mensaje("No se eligieron características para ocultar.").transformar();
        }
    }


/* ==============================================================================================================
    Administrar Usuarios -> El Admin puede agregar o quitar el rol de Admin a otros usuarios (menos a sí mismo)
   ============================================================================================================== */

    public String agregarNuevoAdmin(Request request, Response response) {

        FormAdmins nuevosAdmins = new Gson().fromJson(request.body(), FormAdmins.class);
        System.out.println(request.body());

        if(!nuevosAdmins.getNuevosAdmins().isEmpty()) {

            System.out.println("ID Admin a agregar: " + nuevosAdmins.getNuevosAdmins());
            for(String idNuevoAdmin : nuevosAdmins.getNuevosAdmins()) {
                Usuario nuevoAdmin = repositorioUsuarios.buscar(new Integer(idNuevoAdmin));

                nuevoAdmin.setTipoRol(TipoRol.ADMIN);
                nuevoAdmin.setRol(new Admin());
                repositorioUsuarios.modificar(nuevoAdmin);
            }

            response.status(200);
            return new Mensaje("Se agregaron los nuevos Admins.").transformar();
        }
        else {
            response.status(204);
            return new Mensaje("No se eligió a ningún usuario.").transformar();
        }
    }

    public String quitarAdmin(Request request, Response response) {

        FormAdmins viejosAdmin = new Gson().fromJson(request.body(), FormAdmins.class);
        System.out.println(request.body());

        if(!viejosAdmin.getViejosAdmins().isEmpty()) {

            System.out.println("ID Admin a agregar: " + viejosAdmin.getViejosAdmins());
            for(String idViejoAdmin : viejosAdmin.getViejosAdmins()) {
                Usuario viejoAdmin = repositorioUsuarios.buscar(new Integer(idViejoAdmin));

                viejoAdmin.setTipoRol(TipoRol.USER);
                viejoAdmin.setRol(new User());
                repositorioUsuarios.modificar(viejoAdmin);
            }

            response.status(200);
            return new Mensaje("Se quitaron los permisos de Admin de esos usuarios.").transformar();
        }
        else {
            response.status(204);
            return new Mensaje("No se eligió a ningún usuario.").transformar();
        }
    }

}
