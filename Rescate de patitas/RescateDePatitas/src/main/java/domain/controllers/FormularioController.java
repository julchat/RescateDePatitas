package domain.controllers;

import com.google.gson.Gson;
import domain.business.Sistema;
import domain.business.mascota.*;
import domain.business.notificaciones.Notificador;
import domain.business.notificaciones.NotificadorEmail;
import domain.business.notificaciones.NotificadorSms;
import domain.business.notificaciones.NotificadorWhatsapp;
import domain.business.organizaciones.HogarDeTransito;
import domain.business.ubicacion.Domicilio;
import domain.business.ubicacion.Ubicacion;
import domain.business.users.*;
import domain.repositorios.*;
import domain.repositorios.factories.*;
import domain.security.TipoRol;
import domain.security.User;
import domain.security.Usuario;
import domain.security.password.PasswordStatus;
import domain.security.password.ValidadorPassword;
import json.FormFoundPet;
import json.FormRegisterPet;
import json.FormUser;
import json.Mensaje;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FormularioController {
    private RepositorioUsuarios repositorioUsuarios = FactoryRepositorioUsuarios.get();
    private RepositorioPersonas repositorioPersonas = FactoryRepositorioPersonas.get();
    private RepositorioRescatista repositorioRescatista = FactoryRepositorioRescatista.get();
    private RepositorioDuenio repositorioDuenio = FactoryRepositorioDuenio.get();
    private RepositorioContactos repositorioContactos = FactoryRepositorioContacto.get();
    private RepositorioDomicilios repositorioDomicilios = FactoryRepositorioDomicilios.get();
    private RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();
    private RepositorioChapas repositorioChapas = FactoryRepositorioChapas.get();
    private RepositorioMascotaPerdida repositorioMascotaPerdida = FactoryRepositorioMascotaPerdida.get();
    private RepositorioPublicaciones repositorioPublicaciones = FactoryRepositorioPublicaciones.get();


    public String registrarMascota(Request request, Response response) throws IOException {

        String idSesion = request.headers("Authorization");
        System.out.println("ID SESION: " + idSesion);

        if(idSesion == null) {
            System.out.println("No se ha iniciado sesion.");
            FormRegisterPet formulario = new Gson().fromJson(request.body(), FormRegisterPet.class);
            System.out.println(request.body());

            Duenio duenio = new Duenio();

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

                duenio.getContactos().add(contactoUnico);
                repositorioContactos.agregar(contactoUnico);
            }

            Mascota nuevaMascota = new Mascota();
            nuevaMascota.setNombreMascota(formulario.getNombreMascota());
            nuevaMascota.setApodoMascota(formulario.getApodoMascota());
            nuevaMascota.setEdadMascota(new Integer(formulario.getEdadMascota()));
            nuevaMascota.setTipoAnimal(TipoAnimal.valueOf(formulario.getTipoAnimal()));
            nuevaMascota.setSexoMascota(SexoMascota.valueOf(formulario.getSexoMascota()));
            nuevaMascota.setDescripcionMascota(formulario.getDescripcionMascota());

            Chapa nuevaChapita = new Chapa(duenio, nuevaMascota);
            System.out.println("ID CHAPA: " + nuevaChapita.getId());
            repositorioChapas.agregar(nuevaChapita);

            System.out.println("ID CHAPA: " + nuevaChapita.getId());
            nuevaChapita.generarQR(nuevaChapita.getId());

            duenio.registrarMascota(nuevaChapita);
            repositorioMascotas.agregar(nuevaMascota);
            //repositorioPersonas.agregar(duenio);
            repositorioDuenio.agregar(duenio);


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
            //Persona persona = repositorioPersonas.buscar(usuario.getPersona().getId());
            Duenio duenio = null;

            if(usuario.getPersona().getClass() == Persona.class) {
                duenio = (Duenio) repositorioPersonas.buscar(usuario.getPersona().getId());

                response.status(200);
                System.out.println(new Gson().toJson(duenio));
            }
            else if(usuario.getPersona().getClass() == Duenio.class) {
                duenio = repositorioDuenio.buscar(usuario.getPersona().getId());

                response.status(200);
                System.out.println(new Gson().toJson(duenio));
            }


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

            nuevaMascota.setDescripcionMascota(formulario.getDescripcionMascota());

            Chapa nuevaChapita = new Chapa(duenio, nuevaMascota);
            System.out.println("ID CHAPA: " + nuevaChapita.getId());
            repositorioChapas.agregar(nuevaChapita);

            System.out.println("ID CHAPA: " + nuevaChapita.getId());
            nuevaChapita.generarQR(nuevaChapita.getId());

            (duenio).registrarMascota(nuevaChapita);
            repositorioMascotas.agregar(nuevaMascota);
            //repositorioPersonas.modificar(persona);
            repositorioDuenio.agregar(duenio);

            response.status(200);
            return new Mensaje("Se ha registrado la mascota de forma satisfactoria.").transformar();
        }
    }



/* ==============================================================================================================
    Mascota Perdida pero con Chapita -> Escaneando el Código QR se llega a este Formulario
   ============================================================================================================== */

    public String mascotaPerdidaChapita(Request request, Response response) {

        int idChapita = new Integer(request.params("id"));
        System.out.println("ID CHAPA: " + idChapita);
        RepositorioChapas repositorioChapas = FactoryRepositorioChapas.get();
        Chapa chapita = repositorioChapas.buscarChapa(idChapita);

        FormUser formUser = new Gson().fromJson(request.body(), FormUser.class);
        System.out.println(request.body());

        Rescatista rescatista = new Rescatista();
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
        repositorioRescatista.agregar(rescatista);

        chapita.notificarDuenio(rescatista);

        response.status(200);
        return new Mensaje("El formulario se ha enviado correctamente. El dueño de la mascota se comunicará con usted pronto.").transformar();
    }

/* ==============================================================================================================
    Mascota Perdida pero sin Chapita -> El Rescatista tiene que rellenar este Formulario y crea una Publicacion
   ============================================================================================================== */

    public String mascotaPerdida(Request request, Response response) throws IOException {
        Sistema miSistema = Sistema.getInstance();

        String idSesion = request.headers("Authorization");
        System.out.println("ID SESION: " + idSesion);

        if(idSesion == null) {
            System.out.println("No se ha iniciado sesion.");
            FormFoundPet formulario = new Gson().fromJson(request.body(), FormFoundPet.class);
            System.out.println(request.body());

            Rescatista rescatista = new Rescatista();
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
                //rescatista.getDomicilio().setUbicacion(new Ubicacion());
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

            if(formulario.getContactoNombre() != "" &&
                    formulario.getContactoApellido() != "" &&
                    formulario.getContactoEmail() != "" &&
                    formulario.getContactoTelefono() != ""){
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

                rescatista.getContactos().add(contactoUnico);
                repositorioContactos.agregar(contactoUnico);
            }

            MascotaPerdida mascotaPerdida = new MascotaPerdida();
            mascotaPerdida.setTamanio(Tamanio.valueOf(formulario.getTamanioMascota()));
            mascotaPerdida.setTipoAnimal(TipoAnimal.valueOf(formulario.getTipoAnimal()));
            mascotaPerdida.setSexoMascota(SexoMascota.valueOf(formulario.getSexoMascota()));
            mascotaPerdida.setDescripcion(formulario.getDescripcionMascota());
            //TODO: Ubicacion y caracteristicas

            repositorioRescatista.agregar(rescatista);
            repositorioMascotaPerdida.agregar(mascotaPerdida);


            // En este caso, el Rescatista se involucra y puede albergar a la mascota
            if(formulario.getTransito().equals("si")) {
                rescatista.setPuedeAlojarMascota(true);
                rescatista.alojarMascota(mascotaPerdida);
            }
            // En este caso, se le buscara un Hogar de Transito adecuado para albergar a la mascota
            else {
                int radioKM = new Integer(formulario.getRadioKM());

                HogarDeTransito hogarAdecuado = miSistema.buscarHogarMasCercano(radioKM, mascotaPerdida);
                hogarAdecuado.alojarMascota(mascotaPerdida);
            }

            //TODO: crear publicacion
            rescatista.reportarMascotaPerdida(mascotaPerdida);

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
            Rescatista rescatista = null;

            if(usuario.getPersona().getClass() == Persona.class) {
                rescatista = (Rescatista) repositorioPersonas.buscar(usuario.getPersona().getId());

                response.status(200);
                System.out.println(new Gson().toJson(rescatista));

            }
            else if(usuario.getPersona().getClass() == Rescatista.class) {
                rescatista = repositorioRescatista.buscar(usuario.getPersona().getId());

                response.status(200);
                System.out.println(new Gson().toJson(rescatista));
            }

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
            //TODO: Ubicacion y caracteristicas

            repositorioMascotaPerdida.agregar(mascotaPerdida);

            // En este caso, el Rescatista se involucra y puede albergar a la mascota
            if(formulario.getTransito().equals("si")) {
                rescatista.setPuedeAlojarMascota(true);
                rescatista.alojarMascota(mascotaPerdida);
            }
            // En este caso, se le buscara un Hogar de Transito adecuado para albergar a la mascota
            else {
                int radioKM = new Integer(formulario.getRadioKM());

                HogarDeTransito hogarAdecuado = miSistema.buscarHogarMasCercano(radioKM, mascotaPerdida);
                hogarAdecuado.alojarMascota(mascotaPerdida);
            }

            //TODO: crear publicacion
            rescatista.reportarMascotaPerdida(mascotaPerdida);

            response.status(200);
            return new Mensaje("Se ha reportado a la mascota de forma satisfactoria.").transformar();
        }
    }









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

        //PublicacionMascotaPerdida publicacion = (PublicacionMascotaPerdida) repositorioPublicaciones.buscar(idPublicacion);

        //Rescatista rescatista = repositorioRescatista.buscar(publicacion.getAutor().getId());
        //MascotaPerdida mascotaPerdida = repositorioMascotaPerdida.buscar(publicacion.getMascotaRescatada().getId());

        //Notificador.getInstance().notificarRescatista(rescatista, persona, mascotaPerdida);

        response.status(200);
        return new Mensaje("El formulario se ha enviado correctamente. El rescatista se comunicará con usted pronto.").transformar();
    }

}
