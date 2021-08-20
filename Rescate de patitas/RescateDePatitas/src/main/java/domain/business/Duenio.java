package domain.business;

import domain.business.caracteristicas.CaracteristicaMascota;
import domain.business.foto.Foto;
import domain.business.notificaciones.Notificacion;
import domain.business.organizaciones.Organizacion;
import excepciones.HayCaracteristicasNoValidasException;

import java.util.Date;
import java.util.List;

public class Duenio extends Persona {
    private Domicilio domicilio;
    private List<Mascota> mascotas;
    //TODO consultar si un dueño tiene que tener una organizacion
    private Organizacion organizacion;


    // Getters and Setters
    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public List<Mascota> getMascotas(){
        return this.mascotas;
    }

    // Constructor
    public Duenio() {}

    public Duenio(String nombre, String apellido, Date fechaDeNacimiento, TipoDoc tipoDocumento, int numeroDocumento, int telefono, String email, List<Notificacion> formasDeNotificacion, List<Contacto> contactos, Domicilio domicilio, List<Mascota> mascotas) {
        super(nombre, apellido, fechaDeNacimiento, tipoDocumento, numeroDocumento, telefono, email, formasDeNotificacion, contactos);
        this.domicilio = domicilio;
        this.mascotas = mascotas;
    }


    // TODO queda pendiente saber si un dueño depende de una organizacion, si no depende de eso, entonces hay que cambiar los metodos de registrar mascota y lo relacionado a una Organizacion
    // Metodos
    public void registrarMascota(String nombre, TipoAnimal tipo, String apodo, int edadMascota, SexoMascota sexo, String descripcionMascota, List<Foto> fotos, boolean perdida, List<CaracteristicaMascota> caracteristicas) {
       // if(this.getUsuario().getRol().puedoRegistrarMascota()) {
            if(!(caracteristicas.stream().allMatch(unaCaracteristica -> unaCaracteristica.soyCaracteristicaValida(organizacion)))) {
                throw new HayCaracteristicasNoValidasException();
            }
            fotos.forEach(unaFoto-> unaFoto.normalizarA(organizacion.getDimensionEstandar()));
            Mascota mascotaARegistrar = new Mascota(nombre, tipo, edadMascota, sexo, descripcionMascota, fotos, caracteristicas, perdida, true, this);
            mascotas.add(mascotaARegistrar);
       // }
        //else {
         //   System.out.println("[ERROR] No tiene los permisos necesarios para registrar una mascota.");
       // }
    }

    public void cambiarDomicilio(Domicilio nuevoDomicilio){
        this.cambiarOrganizacion(domicilio.buscarOrganizacionMasCercana());
        this.setDomicilio(nuevoDomicilio);
    }

    public void cambiarOrganizacion(Organizacion nuevaOrganizacion){
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
