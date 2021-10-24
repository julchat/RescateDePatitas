package domain.controllers;

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
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FormularioController {

    public ModelAndView showRegistroMascota(Request request, Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"registrar-mascota.hbs");
    }

    public Response registrarMascota(Request request, Response response) {

        RepositorioDuenio repositorioDuenios = FactoryRepositorioDuenio.get();
        RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();

        try {
            Duenio duenio = new Duenio();
            this.registrarDuenio(duenio, request);
            repositorioDuenios.agregar(duenio);

            Mascota mascota = new Mascota();
            this.registrarMascota(mascota, request);
            repositorioMascotas.agregar(mascota);

            response.status(200);
            // Todo: mandar mensaje de que el registro se realizo de forma correcta (?
            response.redirect("/formulario-ok");
        }
        catch (Exception e) {

        }
        finally {
            return response;
        }
    }

    public void registrarDuenio(Duenio duenio, Request request){

        if(request.queryParams("nombre") != null){
            duenio.setNombre(request.queryParams("nombre"));
        }

        if(request.queryParams("apellido") != null){
            duenio.setApellido(request.queryParams("apellido"));
        }

        if(request.queryParams("fechaDeNacimiento") != null && !request.queryParams("fechaDeNacimiento").isEmpty()){
            LocalDate fechaDeNacimiento = LocalDate.parse(request.queryParams("fechaDeNacimiento"));
            duenio.setFechaDeNacimiento(fechaDeNacimiento);
        }

        /*      DEBERIA DAR LO QUE ELIGE DE LA LISTA DE TIPOS DE DOC
        if(request.queryParams("tipoDoc") != null){
            duenio.setTipoDocumento();
        }*/

        if(request.queryParams("nroDocumento") != null){
            duenio.setNumeroDocumento(new Integer(request.queryParams("nroDocumento")));
        }

        if(request.queryParams("email") != null){
            duenio.setEmail(request.queryParams("email"));
        }

        if(request.queryParams("telefono") != null){
            duenio.setTelefono(request.queryParams("telefono"));
        }

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
    }

    public void registrarMascota(Mascota mascota, Request request){

        if(request.queryParams("tipoAnimal") != null){
            mascota.setTipoAnimal(TipoAnimal.valueOf(request.queryParams("tipoAnimal")));
        }

        if(request.queryParams("nombreMascota") != null){
            mascota.setNombreMascota(request.queryParams("nombreMascota"));
        }

        if(request.queryParams("apodoMascota") != null){
            mascota.setApodoMascota(request.queryParams("apodoMascota"));
        }

        if(request.queryParams("edadMascota") != null){
            mascota.setEdadMascota(new Integer(request.queryParams("edadMascota")));
        }

        if(request.queryParams("sexoMascota") != null){
            mascota.setSexoMascota(SexoMascota.valueOf(request.queryParams("sexoMascota")));
        }

        if(request.queryParams("descripcion") != null){
            mascota.setDescripcionMascota(request.queryParams("descripcion"));
        }

        /*
        if(request.queryParams("fotos") != null){
            mascota.setFotos(request.queryParams("fotos"));
        }

        if(request.queryParams("caracteristicas") != null){
            mascota.setCaracteristicasMascota(request.queryParams("caracteristicas"));
        }*/
    }



/* ==============================================================================================================
    Mascota Perdida pero con Chapita -> Escaneando el Código QR se llega a este Formulario
   ============================================================================================================== */

    public ModelAndView showMascotaPerdidaChapita(Request request , Response response) {
        Map<String, Object> parametros = new HashMap<>();
        RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();

        Mascota mascota = repositorioMascotas.buscar(new Integer(request.params("id")));
        parametros.put("mascota", mascota);

        return new ModelAndView(parametros,"reportar-mascota-chapita.hbs");
    }

    public Response mascotaPerdidaChapita(Request request, Response response) {

        RepositorioMascotas repositorioMascotas = FactoryRepositorioMascota.get();
        RepositorioChapas repositorioChapas = FactoryRepositorioChapas.get();
        RepositorioPersonas repositorioPersonas = FactoryRepositorioPersonas.get();

        // Rescatista que se obtiene con los datos del formulario
        Rescatista rescatista = new Rescatista();
        this.registrarRescatista(rescatista, request);


        //==============================================================================================================
        // Todo: en el formulario pide los datos de la mascota que encontró
        //  pero si resulta que la encuentra por el ID de la Chapa, no tienen relevancia los datos que ingresa de la mascota
        MascotaPerdida mascotaPerdida = new MascotaPerdida();
        this.registrarMascotaPerdida(mascotaPerdida, request);
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

    public ModelAndView showMascotaPerdida(Request request , Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"reportar-mascota.hbs");
    }

    public Response mascotaPerdida(Request request, Response response) {
        Sistema miSistema = Sistema.getInstance();

        RepositorioRescatista repositorioRescatistas = FactoryRepositorioRescatista.get();
        RepositorioMascotaPerdida repositorioMascotasPerdidas = FactoryRepositorioMascotaPerdida.get();

        // Crea a un Rescatista y Mascota Perdida, y los persiste en la BD
        // Crea una publicacion con los datos requeridos, tambien la persiste a la BD
        // Verificar si el Rescatista puede alojar a la mascota, sino buscar un Hogar de Transito
        try {
            Rescatista rescatista = new Rescatista();
            this.registrarRescatista(rescatista, request);
            repositorioRescatistas.agregar(rescatista);

            MascotaPerdida mascotaPerdida = new MascotaPerdida();
            this.registrarMascotaPerdida(mascotaPerdida, request);
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

            response.status(200);
            response.redirect("/formulario-ok");
        }
        catch (Exception e) {
            response.status(204);
        }
        finally {
            return response;
        }
    }

    public void registrarRescatista(Rescatista rescatista, Request request){

        if(request.queryParams("nombre") != null){
            rescatista.setNombre(request.queryParams("nombre"));
        }

        if(request.queryParams("apellido") != null){
            rescatista.setApellido(request.queryParams("apellido"));
        }

        if(request.queryParams("fechaDeNacimiento") != null && !request.queryParams("fechaDeNacimiento").isEmpty()){
            LocalDate fechaDeNacimiento = LocalDate.parse(request.queryParams("fechaDeNacimiento"));
            rescatista.setFechaDeNacimiento(fechaDeNacimiento);
        }

        if(request.queryParams("tipoDoc") != null){
            rescatista.setTipoDocumento(TipoDoc.valueOf(request.queryParams("tipoDoc")));
        }

        if(request.queryParams("nroDocumento") != null){
            rescatista.setNumeroDocumento(new Integer(request.queryParams("nroDocumento")));
        }

        if(request.queryParams("email") != null){
            rescatista.setEmail(request.queryParams("email"));
        }

        if(request.queryParams("telefono") != null){
            rescatista.setTelefono(request.queryParams("telefono"));
        }

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
        }

        if(request.queryParams("puedeAlojarMascota") != null){
            rescatista.isPuedeAlojarMascota(request.queryParams("puedeAlojarMascota"));
        }*/
    }

    public void registrarMascotaPerdida(MascotaPerdida mascotaPerdida, Request request){

        if(request.queryParams("descripcion") != null){
            mascotaPerdida.setDescripcion(request.queryParams("descripcion"));
        }

        if(request.queryParams("tipoAnimal") != null){
            mascotaPerdida.setTipoAnimal(TipoAnimal.valueOf(request.queryParams("tipoAnimal")));
        }

        if(request.queryParams("tamanio") != null){
            mascotaPerdida.setTamanio(Tamanio.valueOf(request.queryParams("tamanio")));
        }

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


    }
}
