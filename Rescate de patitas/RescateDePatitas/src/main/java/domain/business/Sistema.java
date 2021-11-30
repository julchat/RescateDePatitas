package domain.business;

import domain.business.mascota.Mascota;
import domain.business.mascota.MascotaPerdida;
import domain.business.organizaciones.HogarDeTransito;
import domain.business.organizaciones.OrdenarPorCercania;
import domain.business.organizaciones.Organizacion;
import domain.business.organizaciones.apiHogares.APIhogares;
import domain.business.organizaciones.apiHogares.entidades.Hogar;
import domain.business.publicaciones.Publicacion;
import domain.business.ubicacion.Lugar;
import domain.repositorios.RepositorioChapas;
import domain.repositorios.factories.FactoryRepositorioChapas;
import domain.security.*;
import domain.security.password.ValidadorPassword;

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

    public List<Publicacion> getPublicaciones() { return publicaciones; }

    public List<Organizacion> getOrganizaciones() { return organizaciones; }

    public List<HogarDeTransito> getHogaresDeTransito() { return hogaresDeTransito; }


    // Metodos
    public boolean validarContrasenia(String usuario, String contrasenia) throws FileNotFoundException {
        return validador.esValida(usuario, contrasenia);
    }

    public Rol validarRol(TipoRol tipoRol) {
        if(tipoRol == TipoRol.ADMIN) {
            return new Admin();
        }
        else if(tipoRol == TipoRol.MODERADOR) {
            return new Moderador();
        }
        else if(tipoRol == TipoRol.USER) {
            return new User();
        }
        else {
            return null;
        }
    }


    public HogarDeTransito buscarHogarMasCercano(int radio, MascotaPerdida mascotaEncontrada) {

        Lugar ubicacionMascota = mascotaEncontrada.getLugarEncontrada();
        List<HogarDeTransito> hogaresCercanos = this.getHogaresDeTransito().stream().filter(hogarDeTransito -> hogarDeTransito.distancia(hogarDeTransito.getLugar().getLatitud(), hogarDeTransito.getLugar().getLongitud(), ubicacionMascota.getLatitud(), ubicacionMascota.getLongitud()) <= radio*1000).collect(Collectors.toList());
        HogarDeTransito hogarAdecuado = null;

        OrdenarPorCercania ordenador = new OrdenarPorCercania(ubicacionMascota);
        List<HogarDeTransito> hogaresFiltrados = hogaresCercanos.stream().filter(hogarDeTransito -> hogarDeTransito.permiteMascotaPerdida(mascotaEncontrada)).collect(Collectors.toList());
        hogarAdecuado = hogaresFiltrados.stream().sorted((o1, o2) -> (ordenador.compare(o1,o2))).collect(Collectors.toList()).get(0);

        if(hogarAdecuado != null) {
            System.out.println("NOMBRE: " + hogarAdecuado.getNombreOrganizacion());
            System.out.println("UBICACION: ");
            System.out.println("    - DIRECCION: " + hogarAdecuado.getLugar().getDireccion());
            System.out.println("    - LATITUD: " + hogarAdecuado.getLugar().getLatitud());
            System.out.println("    - LONGITUD: " + hogarAdecuado.getLugar().getLongitud());
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
}
