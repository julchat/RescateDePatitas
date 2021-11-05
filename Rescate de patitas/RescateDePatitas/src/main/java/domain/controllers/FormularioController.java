package domain.controllers;

import com.google.gson.Gson;
import domain.business.Sistema;
import domain.business.mascota.*;
import domain.business.notificaciones.Notificador;
import domain.business.organizaciones.HogarDeTransito;
import domain.business.ubicacion.Domicilio;
import domain.business.ubicacion.Ubicacion;
import domain.business.users.Duenio;
import domain.business.users.Persona;
import domain.business.users.Rescatista;
import domain.business.users.TipoDoc;
import domain.repositorios.*;
import domain.repositorios.factories.*;
import json.FormUser;
import json.Mensaje;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FormularioController {

    public String registrarMascotaPost(Request request, Response response) {

        return null;
    }


    public String registrarMascota(Request request, Response response) {

        // Todo: los campos para registrar a una persona podrían ser opcionales en el caso que el usuario ya se registró
        RepositorioDuenio repositorioDuenios = FactoryRepositorioDuenio.get();
        RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();

        FormUser formUser = new Gson().fromJson(request.body(), FormUser.class);
        System.out.println(request.body());

        String nombreUsuario = formUser.getUserName();
        String password = formUser.getPassword();
        String passwordConfirm = formUser.getPassConf();

        Duenio duenio = new Duenio();

        duenio.setNombre(formUser.getNombre());
        duenio.setApellido(formUser.getApellido());
        LocalDate fechaDeNacimiento = LocalDate.parse(formUser.getFechaDeNacimiento());
        duenio.setFechaDeNacimiento(fechaDeNacimiento);
        duenio.setTipoDocumento(TipoDoc.valueOf(formUser.getTipoDoc()));
        duenio.setNumeroDocumento(new Integer(formUser.getNroDocumento()));
        duenio.setEmail(formUser.getEmail());
        duenio.setTelefono(formUser.getTelefono());

        /* hay que abrir un nuevo formulario, para poder ingresar Calle, Numeracion, Localidad, etc.
        if(request.queryParams("domicilio") != null){
            duenio.setDomicilio(request.queryParams("domicilio"));
        }

        if(request.queryParams("formasDeNotifacion") != null){
            duenio.setFormasDeNotificacion(request.queryParams("formasDeNotifacion"));
        }

        if(request.queryParams("contactos") != null){
            duenio.setContactos(request.queryParams("contactos"));
        }*/
            duenio.setDomicilio(new Domicilio());
            if(request.queryParams("provincia") != null
                    && request.queryParams("localidad") != null
                    && request.queryParams("codigoPostal") != null
                    && request.queryParams("calle") != null
                    && request.queryParams("numeracion") != null){
                duenio.getDomicilio().setProvincia(request.queryParams("provincia"));
                duenio.getDomicilio().setLocalidad(request.queryParams("localidad"));
                duenio.getDomicilio().setCodigoPostal(new Integer(request.queryParams("codigoPostal")));
                duenio.getDomicilio().setCalle(request.queryParams("calle"));
                duenio.getDomicilio().setNumero(new Integer(request.queryParams("numeracion")));
            }

            if(request.queryParams("departamento") != null && request.queryParams("piso") != null) {
                duenio.getDomicilio().setDepartamento(new Integer(request.queryParams("departamento")));
                duenio.getDomicilio().setPiso(new Integer(request.queryParams("piso")));
            }
            repositorioDuenios.agregar(duenio);
            //repositorioDomicilio.agregar(duenio.getDomicilio());

            Mascota mascota = new Mascota();

            mascota.setNombreMascota(request.queryParams("nombreMascota"));
            mascota.setApodoMascota(request.queryParams("apodoMascota"));
            mascota.setTipoAnimal(TipoAnimal.valueOf(request.queryParams("tipoAnimal")));
            mascota.setSexoMascota(SexoMascota.valueOf(request.queryParams("sexoMascota")));
            mascota.setEdadMascota(new Integer(request.queryParams("edadMascota")));
            mascota.setDescripcionMascota(request.queryParams("descripcionMascota"));


            /*
            if(request.queryParams("fotos") != null){
                mascota.setFotos(request.queryParams("fotos"));
            }

            if(request.queryParams("caracteristicas") != null){
                mascota.setCaracteristicasMascota(request.queryParams("caracteristicas"));
            }*/

            repositorioMascotas.agregar(mascota);

            System.out.println("Se ha creado el usuario de forma satisfactoria!!");
            return new Mensaje("Se ha creado el usuario de forma satisfactoria.").transformar();
    }



/* ==============================================================================================================
    Mascota Perdida pero con Chapita -> Escaneando el Código QR se llega a este Formulario
   ============================================================================================================== */

    public Response mascotaPerdidaChapita(Request request, Response response) {

        RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();
        RepositorioChapas repositorioChapas = FactoryRepositorioChapas.get();
        RepositorioPersonas repositorioPersonas = FactoryRepositorioPersonas.get();
        RepositorioRescatista repositorioRescatistas = FactoryRepositorioRescatista.get();

        // Rescatista que se obtiene con los datos del formulario
        Rescatista rescatista = new Rescatista();
        rescatista.setNombre(request.queryParams("nombre"));
        rescatista.setApellido(request.queryParams("apellido"));
        rescatista.setFechaDeNacimiento(LocalDate.parse(request.queryParams("fechaDeNacimiento")));
        rescatista.setTipoDocumento(TipoDoc.valueOf(request.queryParams("tipoDoc")));
        rescatista.setNumeroDocumento(new Integer(request.queryParams("nroDocumento")));
        rescatista.setEmail(request.queryParams("email"));
        rescatista.setTelefono(request.queryParams("telefono"));

        if(request.queryParams("provincia") != null &&
                request.queryParams("localidad") != null &&
                request.queryParams("codigoPostal") != null &&
                request.queryParams("calle") != null &&
                request.queryParams("numeracion") != null){
            rescatista.setDomicilio(
                    new Domicilio(request.queryParams("provincia"),
                            request.queryParams("localidad"),
                            new Integer(request.queryParams("codigoPostal")),
                            request.queryParams("calle"),
                            new Integer(request.queryParams("numeracion")),
                            new Integer(request.queryParams("departamento")),
                            new Integer(request.queryParams("piso")),
                            new Ubicacion(
                                    new Integer(request.queryParams("longitud")),
                                    new Integer(request.queryParams("latitud"))))
            );
        }
        // Todo: tal vez se puede utilizar una API, para buscar por la direccion y asi obtener la Ubicacion en coordenadas

        /*
        if(request.queryParams("formasDeNotifacion") != null){
            rescatista.setFormasDeNotificacion(request.queryParams("formasDeNotifacion"));
        }

        if(request.queryParams("contactos") != null){
            rescatista.setContactos(request.queryParams("contactos"));
        }*/


        /*if(request.queryParams("albergarMascota") != null){
            rescatista.isPuedeAlojarMascota(request.queryParams("albergarMascota"));
        }*/

        repositorioRescatistas.agregar(rescatista);

        //==============================================================================================================
        // Todo: en el formulario pide los datos de la mascota que encontró
        //  pero si resulta que la encuentra por el ID de la Chapa, no tienen relevancia los datos que ingresa de la mascota
        MascotaPerdida mascotaPerdida = new MascotaPerdida();
        mascotaPerdida.setDescripcion(request.queryParams("descripcionMascota"));
        mascotaPerdida.setTipoAnimal(TipoAnimal.valueOf(request.queryParams("tipoAnimal")));
        mascotaPerdida.setTamanio(Tamanio.valueOf(request.queryParams("tamanioAnimal")));

        /*
        if(request.queryParams("fotos") != null){
            mascotaPerdida.setCarrouselFotos(request.queryParams("fotos"));
        }*/

        // TODO: para este caso tendría que elegirlo desde un mapa, podemos cargar una ubicacion X,Y, o directamente con una direccion como String
        if(request.queryParams("latitud") != null && request.queryParams("longitud") != null){
            mascotaPerdida.setUbicacionEncontrada(new Ubicacion(
                    new Integer(request.queryParams("longitud")),
                    new Integer(request.queryParams("latitud"))));
        }
        //==============================================================================================================


        // Obtener el ID que esta en el link /reportar-mascota/{id}, con ese id busco la chapita
        int idChapa = new Integer(request.queryParams("idChapa"));

        Chapa chapaRecuperada = repositorioChapas.buscarChapa(idChapa);

        Mascota mascotaRecuperada = repositorioMascotas.buscarMascotaChapita(chapaRecuperada.getMascota().getId());
        Duenio duenioMascota = repositorioPersonas.buscarDuenio(chapaRecuperada.getDuenio().getId());

        Notificador notificador = Notificador.getInstance();
        notificador.notificarDuenio(duenioMascota, rescatista, mascotaRecuperada);

        response.status(200);
        response.redirect("/ok");       // Mostrar que se envió correctamente (?)
        return response;
    }

/* ==============================================================================================================
    Mascota Perdida pero sin Chapita -> El Rescatista tiene que rellenar este Formulario y crea una Publicacion
   ============================================================================================================== */

    public Response mascotaPerdida(Request request, Response response) {
        Sistema miSistema = Sistema.getInstance();

        RepositorioRescatista repositorioRescatistas = FactoryRepositorioRescatista.get();
        RepositorioMascotaPerdida repositorioMascotasPerdidas = FactoryRepositorioMascotaPerdida.get();

        // Crea a un Rescatista y Mascota Perdida, y los persiste en la BD
        // Crea una publicacion con los datos requeridos, tambien la persiste a la BD
        // Verificar si el Rescatista puede alojar a la mascota, sino buscar un Hogar de Transito
        try {
            Rescatista rescatista = new Rescatista();

            rescatista.setNombre(request.queryParams("nombre"));
            rescatista.setApellido(request.queryParams("apellido"));
            rescatista.setFechaDeNacimiento(LocalDate.parse(request.queryParams("fechaDeNacimiento")));
            rescatista.setTipoDocumento(TipoDoc.valueOf(request.queryParams("tipoDoc")));
            rescatista.setNumeroDocumento(new Integer(request.queryParams("nroDocumento")));
            rescatista.setEmail(request.queryParams("email"));
            rescatista.setTelefono(request.queryParams("telefono"));

            if(request.queryParams("provincia") != null &&
                    request.queryParams("localidad") != null &&
                    request.queryParams("codigoPostal") != null &&
                    request.queryParams("calle") != null &&
                    request.queryParams("numeracion") != null
            ){
                rescatista.setDomicilio(
                        new Domicilio(request.queryParams("provincia"),
                                request.queryParams("localidad"),
                                new Integer(request.queryParams("codigoPostal")),
                                request.queryParams("calle"),
                                new Integer(request.queryParams("numeracion")),
                                new Integer(request.queryParams("departamento")),
                                new Integer(request.queryParams("piso")),
                                new Ubicacion(
                                        new Integer(request.queryParams("longitud")),
                                        new Integer(request.queryParams("latitud"))))
                );
            }
            // Todo: tal vez se puede utilizar una API, para buscar por la direccion y asi obtener la Ubicacion en coordenadas

            /*
            if(request.queryParams("formasDeNotifacion") != null){
                rescatista.setFormasDeNotificacion(request.queryParams("formasDeNotifacion"));
            }

            if(request.queryParams("contactos") != null){
                rescatista.setContactos(request.queryParams("contactos"));
            }*/


            /*
            if(request.queryParams("albergarMascota") != null){
                rescatista.isPuedeAlojarMascota(request.queryParams("albergarMascota"));
            }*/

            repositorioRescatistas.agregar(rescatista);

            MascotaPerdida mascotaPerdida = new MascotaPerdida();
            mascotaPerdida.setDescripcion(request.queryParams("descripcionMascota"));
            mascotaPerdida.setTipoAnimal(TipoAnimal.valueOf(request.queryParams("tipoAnimal")));
            mascotaPerdida.setTamanio(Tamanio.valueOf(request.queryParams("tamanioAnimal")));

            /*
            if(request.queryParams("fotos") != null){
                mascotaPerdida.setCarrouselFotos(request.queryParams("fotos"));
            }*/

            // TODO: para este caso tendría que elegirlo desde un mapa, podemos cargar una ubicacion X,Y, o directamente con una direccion como String
            if(request.queryParams("latitud") != null && request.queryParams("longitud") != null){
                mascotaPerdida.setUbicacionEncontrada(new Ubicacion(
                        new Integer(request.queryParams("longitud")),
                        new Integer(request.queryParams("latitud"))));
            }


            repositorioMascotasPerdidas.agregar(mascotaPerdida);

            if(rescatista.isPuedeAlojarMascota()) {
                rescatista.alojarMascota(mascotaPerdida);
            }
            else {
                int radioKM = new Integer(request.queryParams("radioKM"));

                HogarDeTransito hogarAdecuado = miSistema.buscarHogarMasCercano(radioKM, mascotaPerdida);
                hogarAdecuado.alojarMascota(mascotaPerdida);
            }

            rescatista.reportarMascotaPerdida(mascotaPerdida);


            response.redirect("/");
        }
        catch (Exception e) {
            response.status(204);
        }
        finally {
            return response;
        }
    }

}
