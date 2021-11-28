package domain.business.notificaciones;
import domain.business.EntidadPersistente;
import domain.business.mascota.Mascota;
import domain.business.mascota.MascotaPerdida;
import domain.business.publicaciones.PublicacionParaAdopcion;
import domain.business.users.Persona;
import domain.business.publicaciones.PublicacionMascotaEnAdopcion;

import javax.persistence.*;

@Entity
@Table(name = "notificacion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "medio")
public abstract class Notificacion extends EntidadPersistente {

    //@Enumerated(EnumType.STRING)
    //@Column(name = "tipoDeNotificacion")
    @Transient
    private TipoNotificacion tipoDeNotificacion;

    @Transient
    AdapterTwillio twillio = new AdapterTwillio();

    @Transient
    AdapterJavaMailApi mailSender = new AdapterJavaMailApi();


    // Metodos
    public Notificacion(TipoNotificacion tipoDeNotificacion) {
        this.tipoDeNotificacion = tipoDeNotificacion;
    }

    public TipoNotificacion obtenerCodigoNotificacion() {
        return this.tipoDeNotificacion;
    }

    public String armarMensajeMascotaEncontrada(Persona destinatario, Persona hallador, Mascota mascotaEncontrada) {
        return "Estimado/a " + destinatario.getNombre() + "\n" + "Nos comunicamos para informarle que su mascota " + mascotaEncontrada.getNombreMascota() +
                " ha sido encontrada por " + hallador.getApellido() + " " + hallador.getNombre() + ".\n";
    }

    public String armarMensajeMascotaEncontradaRescatista(Persona destinatario, Persona duenio, MascotaPerdida mascotaPerdida) {
        return "Estimado/a " + destinatario.getNombre() + "\n" + "Nos comunicamos para informarle que " + duenio.getApellido() + " " + duenio.getNombre() +
                 " ha encontrado a su mascota.\n";
    }

    public String armarMensajeHayInteresadoEnAdoptar(Persona destinatario, Persona interesado, Mascota mascotaPorSerAdoptada){
        return "Estimado/a " + destinatario.getNombre() + "\n" + "Nos comunicamos para informarle que el usuario " + interesado.getApellido() + " " + interesado.getNombre() +
                "esta interesado/a en adoptar a su mascota " + mascotaPorSerAdoptada.getNombreMascota() +  ".\n";
    }

    public String armarMensajeRecomendacionesSemanales(Persona destinatario, PublicacionParaAdopcion publicacion, String ruta){
        return "Estimado/a " + destinatario.getNombre() + "\n" + " Nos comunicamos para informarle que hay recomendaciones de mascotas para adoptar relacionadas a su publicacion disponibles!" +
                " para visualizarlas, ingrese a " + ruta;
    }

    public abstract void notificarMascotaEncontrada(Persona destinatario, Persona hallador, Mascota mascotaPerdida);

    public abstract void notificarMascotaEncontradaRescatista(Persona destinatario, Persona duenio, MascotaPerdida mascotaPerdida);

    public abstract void notificarHayInteresadoEnAdoptar(Persona destinatario, Persona interesado, Mascota mascotaPorSerAdoptada);

    public abstract void notificarRecomendaciones(Persona destinatario, PublicacionParaAdopcion publicacion, String ruta);
}
