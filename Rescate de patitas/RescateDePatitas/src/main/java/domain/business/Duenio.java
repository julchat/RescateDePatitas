package domain.business;

import domain.notificaciones.Notificacion;

import java.util.Date;
import java.util.List;

public class Duenio extends Persona {
    private Domicilio domicilio;
    private List<Mascota> mascotas;
    private Organizacion organizacion;
    // Getters and Setters
    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    // Constructor
    public Duenio() {}

    public Duenio(String nombre, String apellido, Date fechaDeNacimiento, String tipoDocumento, int numeroDocumento, int telefono, String email, List<Notificacion> formasDeNotificacion, List<Contacto> contactos, Domicilio domicilio, List<Mascota> mascotas) {
        super(nombre, apellido, fechaDeNacimiento, tipoDocumento, numeroDocumento, telefono, email, formasDeNotificacion, contactos);
        this.domicilio = domicilio;
        this.mascotas = mascotas;
    }

    // Metodos
    public void registrarMascota(String nombre, TipoAnimal tipo, String apodo, int edadMascota, SexoMascota sexo, String descripcionMascota, List<Foto> fotos, boolean perdida, List<CaracteristicaConValor> caracs) {
        if(!(caracs.stream().allMatch(unaCaracteristica -> unaCaracteristica.soyCaracteristicaValida(organizacion))){
            throw new CaracteristicasNoValidasException();
        }
        fotos.forEach(unaFoto-> unaFoto.normalizarA(organizacion.getDimensionFoto()));
        Mascota mascotaARegistrar = new Mascota(nombre, tipo, edadMascota, sexo, descripcionMascota, fotos, caracs, perdida, true, this);
        mascotas.add(mascotaARegistrar);
    }

    public cambiarDomicilio(Domicilio nuevoDomicilio){
        this.cambiarOrganizacion(domicilio.buscarOrganizacionMasCercana());
        this.setDomicilio(nuevoDomicilio);
    }

    public cambiarOrganizacion(Organizacion nuevaOrganizacion){
        mascotas.forEach(unaMascota -> unaMascota.ajustarseAOrganizacion(nuevaOrganizacion));
        this.setOrganizacion(nuevaOrganizacion);
    }
    public void mascotaEncontrada(Mascota mascotaEncontrada) {
        if(mascotas.contains(mascotaEncontrada)) {
            mascotaEncontrada.serEncontrada();
        }
        else {
            System.out.println("No es la mascota que esta buscando este dueño.");
        }
    }
}
