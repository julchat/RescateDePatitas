package domain.controllers;

import domain.business.mascota.Chapa;
import domain.business.mascota.Mascota;
import domain.business.users.Duenio;
import domain.business.users.Rescatista;
import domain.repositorios.Repositorio;
import domain.repositorios.RepositorioChapas;
import domain.repositorios.RepositorioMascotas;
import domain.repositorios.RepositorioUsuarios;
import domain.repositorios.factories.*;
import domain.security.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class FormularioController {


/*
    Mascota Perdida pero con Chapita -> Escaneando el Código QR se llega a este Formulario
*/
    public ModelAndView showMascotaPerdidaChapita(Request request , Response response) {
        Map<String, Object> parametros = new HashMap<>();
        RepositorioMascotas repoMascotas = FactoryRepositorioMascota.get();

        Mascota mascota = repoMascotas.buscar(new Integer(request.params("id")));
        parametros.put("mascota", mascota);

        return new ModelAndView(parametros,"reportar-mascota-chapita.hbs");
    }

    public Response mascotaPerdidaChapita(Request request, Response response) {

        RepositorioMascotas repoMascotas = FactoryRepositorioMascota.get();
        RepositorioChapas repoChapas = FactoryRepositorioChapas.get();
        RepositorioPersonas repositorioPersonas = FactoryRepositorioPersonas.get();

        // Rescatista que se obtiene con los datos del formulario
        Rescatista nuevoRescatista = new Rescatista();

        // Obtener el ID que esta en el link /reportar-mascota/{id}, con ese id busco la chapita
        int idChapa = 0;

        Chapa chapaRecuperada = repoChapas.buscarChapa(idChapa);

        Mascota mascotaRecuperada = repoMascotas.buscarMascotaChapita(chapaRecuperada.getMascota().getId());
        Duenio duenioMascota = repositorioPersonas.buscarDuenio(chapaRecuperada.getDuenio().getId());

        duenioMascota.notificarDuenio(nuevoRescatista, mascotaRecuperada);

        return response;
    }

/*
    Mascota Perdida pero sin Chapita -> El Rescatista tiene que rellenar este Formulario y crear una Publicacion
*/

    public ModelAndView showMascotaPerdida(Request request , Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        return new ModelAndView(viewModel,"reportar-mascota.hbs");
    }

    public Response mascotaPerdida(Request request, Response response) {

        // Crea a un Rescatista y Mascota Perdida, y los persiste en la BD
        // Crea una publicacion con los datos requeridos, tambien la persiste a la BD
        // Verificar si el Rescatista puede alojar a la mascota, sino buscar un Hogar de Transito

        response.redirect("/reportar-mascota");
        return response;
    }
}
