package domain.business;

import database.EntityManagerHelper;
import domain.business.mascota.Chapa;
import domain.business.mascota.Mascota;
import domain.business.mascota.MascotaPerdida;
import domain.business.organizaciones.HogarDeTransito;
import domain.business.organizaciones.OrdenarPorCercania;
import domain.business.organizaciones.Organizacion;
import domain.business.organizaciones.apiHogares.APIhogares;
import domain.business.organizaciones.apiHogares.entidades.Hogar;
import domain.business.publicaciones.Publicacion;
import domain.business.ubicacion.Ubicacion;
import domain.business.users.Rescatista;
import domain.repositorios.RepositorioChapas;
import domain.repositorios.factories.FactoryRepositorioChapas;
import domain.security.password.ValidadorPassword;
import domain.security.Usuario;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sistema {
    private static Sistema instancia = null;
    private static List<Usuario> usuarios = new ArrayList<>();
    private List<Publicacion> publicaciones = new ArrayList<>();
    private List<Organizacion> organizaciones = new ArrayList<>();
    private List<HogarDeTransito> hogaresDeTransito = new ArrayList<>();
    private List<Mascota> mascotasRegistradas = new ArrayList<>();
    private List<MascotaPerdida> mascotasPerdidas = new ArrayList<>();
    private List<Mascota> mascotasEnAdopcion = new ArrayList<>();
    private ValidadorPassword validador = new ValidadorPassword();

    RepositorioChapas repositorioChapas = FactoryRepositorioChapas.get();


    {
        APIhogares apIhogares = APIhogares.getInstance();
        try {
             for(int i=1; i<= apIhogares.cantidadPaginas(); i++) {
                List<Hogar> hogares = apIhogares.conjuntoHogares(i);
                for(Hogar hogar : hogares) {
                    HogarDeTransito hogarDeTransito = new HogarDeTransito();
                    hogarDeTransito.mappearHogar(hogar);
                    hogaresDeTransito.add(hogarDeTransito);
                }
             }
        } catch (IOException e) {

        }
    }

    public static Sistema getInstance() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    public void mostrarMascotasPerdidas() {
        for (MascotaPerdida mascota : mascotasPerdidas) {
            mascota.mostrarMascota();
        }
    }

    public void mostrarMascotasEnAdopcion() {
        for(Mascota mascotaEnAdopcion : mascotasEnAdopcion) {
            mascotaEnAdopcion.mostrarDatosMascota();
        }
    }

    public void mostrarMascotasRegistradas() {
        for(Mascota mascotaRegistrada : mascotasRegistradas) {
            mascotaRegistrada.mostrarDatosMascota();
        }
    }

    public void registrarMascotaPerdida(MascotaPerdida mascotaPerdida) {
        this.mascotasPerdidas.add(mascotaPerdida);
    }

    public void registrarMascotaEnAdopcion(Mascota mascotaAdopcion) { this.mascotasEnAdopcion.add(mascotaAdopcion); }

    public void registrarMascota(Mascota mascota) { this.mascotasRegistradas.add(mascota); }

    public List<Usuario> getUsuarios() { return usuarios; }

    public List<Publicacion> getPublicaciones() { return publicaciones; }

    public List<Organizacion> getOrganizaciones() { return organizaciones; }

    public List<HogarDeTransito> getHogaresDeTransito() { return hogaresDeTransito; }


    // Metodos
    public Usuario crearUsuario(String nombreUsuario, String password) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario(nombreUsuario);
        nuevoUsuario.setContrasenia(password);
        if (!this.existeUsuario(nombreUsuario)) {
            this.usuarios.add(nuevoUsuario);
            return nuevoUsuario;
        } else {
            return null;
        }
    }

    public Usuario buscarUsuario(String nombreUsuario) {
        return this.usuarios.stream().filter(usuario -> usuario.getNombreUsuario().equals(nombreUsuario)).findFirst().get();
    }

    public boolean coincideContrasenia(String usuarioBuscado, String contrasenia) {
        Usuario usuario = buscarUsuario(usuarioBuscado);
        if(usuario.getContrasenia().equals(contrasenia)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean existeUsuario(String usuarioBuscado) {
        return this.usuarios.stream().map(usuario -> usuario.getNombreUsuario()).collect(Collectors.toList()).contains(usuarioBuscado);
    }


    public boolean validarContrasenia(String usuario, String contrasenia) throws FileNotFoundException {
        return validador.esValida(usuario, contrasenia);
    }

    /*public boolean usuarioInvalido(String usuario) {
        return !EntityManagerHelper.usuarioDisponible(usuario);
        //return listaDeUsuarios.stream().anyMatch(usuario -> usuario.getNombre().equals(usuarioProvisorio));
    }*/


    // Cuando rescata una mascota y escanea el código QR
    // En este caso, recibe a un Rescatista el cual rellena sus datos y al escanear el código QR te devuelve el ID de chapa de la mascota
    public void recibirFormulario(Rescatista rescatista, MascotaPerdida mascotaPerdida, int id_chapa) {

        // En el caso que tenga la chapita y haya escaneado el código QR, tendra el id_chapa correspondiente
        if(id_chapa != 0) {
            Chapa chapa = repositorioChapas.buscarChapa(id_chapa);
            chapa.getDuenio().notificarDuenio(rescatista, chapa.getMascota());
        }
        // En el caso que no tenga chapita, entonces se crea un formulario y por defecto el id_chapa sera 0
        else {
            // Cuando el rescatista puede alojar a la mascota
            if(rescatista.isPuedeAlojarMascota()) {
                rescatista.alojarMascota(mascotaPerdida);
            }
            // Cuando el rescatista NO puede alojar a la mascota y requiere la busqueda de un Hogar de Transito
            else {
                /* TODO: en este caso, se busca al Hogar de Transito MAS cercano según ¿el Domicilio del Rescatista O de
                la ubicacion donde fue encontrada la Mascota?
                En el siguiente caso es de acuerdo a la ubicacion donde se encuentra la mascota perdida. */

                System.out.println("Seleccione el tipo de animal de la mascota encontrada: ");
                //mascotaPerdida.setTipoAnimal(this.eleccionTipoAnimal(entrada));
                System.out.println("Seleccione el tamaño de la mascota encontrada: ");
                //mascotaPerdida.setTamanio(this.eleccionTamanio(entrada));

                System.out.print("Ingrese un radio en KM para la búsqueda de los Hogares de Transito: ");
                int radio = 0; //= entrada.nextInt();
                HogarDeTransito hogarDeTransito = this.buscarHogarMasCercano(radio, mascotaPerdida);

                if(hogarDeTransito == null) {
                    System.out.println("No hay ningún hogar de tránsito que pueda alojar a la mascota encontrada.");
                    // Todo: "En este radio no se encontraron hogares, busca por un radio mayor"
                    return;
                }
                hogarDeTransito.alojarMascota(mascotaPerdida);
            }
            rescatista.reportarMascotaPerdida(mascotaPerdida);
            this.registrarMascotaPerdida(mascotaPerdida);
        }
    }





    public void reclamarMascotaEncontrada(MascotaPerdida mascota) {
        if(mascotasPerdidas.contains(mascota)) {
            mascotasPerdidas.remove(mascota);
        }
        else {
            System.out.println("La mascota en cuestión no se encuentra en la BD de Mascotas Perdidas.");
        }
    }


    public HogarDeTransito buscarHogarMasCercano(int radio, MascotaPerdida mascotaEncontrada) {

        Ubicacion ubicacionMascota = mascotaEncontrada.getUbicacionEncontrada();
        List<HogarDeTransito> hogaresCercanos = this.getHogaresDeTransito().stream().filter(hogarDeTransito -> hogarDeTransito.distancia(hogarDeTransito.getLatitud(), hogarDeTransito.getLongitud(), ubicacionMascota.getLatitud(), ubicacionMascota.getLongitud()) <= radio*1000).collect(Collectors.toList());
        HogarDeTransito hogarAdecuado = null;

        OrdenarPorCercania ordenador = new OrdenarPorCercania(ubicacionMascota);
        List<HogarDeTransito> hogaresFiltrados = hogaresCercanos.stream().filter(hogarDeTransito -> hogarDeTransito.permiteMascotaPerdida(mascotaEncontrada)).collect(Collectors.toList());
        hogarAdecuado = hogaresFiltrados.stream().sorted((o1, o2) -> (ordenador.compare(o1,o2))).collect(Collectors.toList()).get(0);

        if(hogarAdecuado != null) {
            System.out.println("NOMBRE: " + hogarAdecuado.getNombreOrganizacion());
            System.out.println("UBICACION: ");
            System.out.println("    - DIRECCION: " + hogarAdecuado.getDireccion());
            System.out.println("    - LATITUD: " + hogarAdecuado.getLatitud());
            System.out.println("    - LONGITUD: " + hogarAdecuado.getLongitud());
            System.out.println("TELEFONO: " + hogarAdecuado.getTelefono());
            System.out.println("ADMISIONES: ");
            System.out.println("    - PERROS: " + hogarAdecuado.aceptaPerros());
            System.out.println("    - GATOS: " + hogarAdecuado.aceptaGatos());
            System.out.println("CAPACIDAD: " + hogarAdecuado.getCapacidad());
            System.out.println("LUGARES DISPONIBLES: " + hogarAdecuado.getLugaresDisponibles());
            System.out.println("TIENE PATIO: " + hogarAdecuado.poseePatio());
            System.out.println("CARACTERISTICAS: " + hogarAdecuado.getCaracteristicasAdmitidas());

            System.out.println("-------------------------------------------------------------------");
        }
        return hogarAdecuado;
    }
/*
    public List<Persona> notificarPersonasAdoptantes() {
        // Todo: hecho asi nomas

        List<Publicacion> publicaciones = this.getPublicaciones().stream().filter(publicacion -> publicacion.getClass().equals(PublicacionParaAdoptar.class)).collect(Collectors.toList());

        List<Persona> autores = publicaciones.stream().map(publicacion -> publicacion.getAutor()).collect(Collectors.toList());

        // Todo: por cada autor, enviar una notificacion de una publicacion de mascota que se ajusta a sus preferencias y comodidades
    }*/
}
