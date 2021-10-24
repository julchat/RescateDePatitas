package domain.business.users;

import domain.business.mascota.Chapa;
import domain.business.mascota.Mascota;
import domain.business.ubicacion.Domicilio;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "duenio")
@DiscriminatorValue("duenio")
public class Duenio extends Persona {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "domicilio")
    private Domicilio domicilio;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Column(name = "mascotasACargo")
    private List<Chapa> mascotasACargo = new ArrayList<>();


    // Getters and Setters
    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public List<Chapa> getMascotasACargo() { return mascotasACargo; }

    public void setMascotasACargo(List<Chapa> mascotasACargo) { this.mascotasACargo = mascotasACargo; }

    public Duenio() {}


    // Metodos
    public void registrarMascota(Mascota nuevaMascota) throws IOException {
        Chapa nuevaChapa = new Chapa(this, nuevaMascota);
        this.getMascotasACargo().add(nuevaChapa);
    }

    public void mostrarMascotas() {
        int contador = 0;
        for(Chapa chapaMascota: getMascotasACargo()) {
            System.out.println("Mascota: " + contador);
            chapaMascota.getMascota().mostrarDatosMascota();
            contador++;
        }
    }


    /*public void registrarMascota(String nombre, TipoAnimal tipo, String apodo, int edadMascota, SexoMascota sexo, String descripcionMascota, List<Foto> fotos, boolean perdida, List<CaracteristicaMascota> caracteristicas) {
        if(!(caracteristicas.stream().allMatch(unaCaracteristica -> unaCaracteristica.soyCaracteristicaValida(organizacion)))) {
            throw new HayCaracteristicasNoValidasException();
        }
        fotos.forEach(unaFoto-> unaFoto.normalizarA(organizacion.getDimensionEstandar()));
        Mascota mascotaARegistrar = new Mascota(nombre, tipo, edadMascota, sexo, descripcionMascota, fotos, caracteristicas, perdida, true, this);
        mascotas.add(mascotaARegistrar);
    }*/

    public void cambiarDomicilio(Domicilio nuevoDomicilio){
        //this.cambiarOrganizacion(domicilio.buscarOrganizacionMasCercana());
        this.setDomicilio(nuevoDomicilio);
    }

    @Override
    public void mostrarDatosNoSensibles() {
        super.mostrarDatosNoSensibles();
        System.out.println("Domicilio: ");
        System.out.println("    - Provincia: " + getDomicilio().getProvincia());
        System.out.println("    - Localidad: " + getDomicilio().getLocalidad());
        System.out.println("    - Calle: " + getDomicilio().getCalle());
        System.out.println("    - Numeración: " + getDomicilio().getNumero());
        this.mostrarMascotas();
    }

    public void notificarDuenio(Rescatista rescatista, Mascota mascotaPerdida) {
        // Notifica al Dueño de la Mascota
        this.getFormasDeNotificacion().forEach(notificacion -> notificacion.notificarMascotaEncontrada(this, rescatista, mascotaPerdida));
        // Notifica a cada uno de los Contactos que haya agregado la persona
        if(!this.getContactos().isEmpty()) {
            this.getContactos().forEach(contacto -> contacto.getFormasDeNotificacion().forEach(notificacion -> notificacion.notificarMascotaEncontrada(this, rescatista, mascotaPerdida)));
        }
    }
}
