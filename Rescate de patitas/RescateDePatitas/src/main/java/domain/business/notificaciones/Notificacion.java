package domain.business.notificaciones;
import domain.business.EntidadPersistente;
import domain.business.mascota.Mascota;
import domain.business.users.Persona;
import domain.business.publicaciones.PublicacionParaAdoptar;

import javax.persistence.*;

@Entity
@Table(name = "notificacion")
public abstract class Notificacion extends EntidadPersistente {

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoDeNotificacion")
    private TipoNotificacion tipoDeNotificacion;

    @Transient
    AdapterTwillio twillio = new AdapterTwillio();

    @Transient
    AdapterJavaMailApi mailSender = new AdapterJavaMailApi();

    // Metodos
    public abstract TipoNotificacion obtenerCodigoNotificacion();

    public String armarMensajeMascotaEncontrada(Persona destinatario, Persona hallador, Mascota mascotaEncontrada) {
        return "Estimado/a " + destinatario.getNombre() + "\n" + "Nos comunicamos para informarle que su mascota " + mascotaEncontrada.getNombreMascota() +
                " ha sido encontrada por " + hallador.getApellido() + " " + hallador.getNombre() + ".\n";
    }

    public String armarMensajeHayInteresadoEnAdoptar(Persona destinatario, Persona interesado, Mascota mascotaPorSerAdoptada, String ruta){
        return "Estimado/a " + destinatario.getNombre() + "\n" + "Nos comunicamos para informarle que el usuario " + interesado.getApellido() + " " + interesado.getNombre() +
                "esta interesado/a en adoptar a su mascota " + mascotaPorSerAdoptada.getNombreMascota() +  ".\n Para mas informacion ingrese a " + ruta;
    }

    public String armarMensajeRecomendacionesSemanales(Persona destinatario, PublicacionParaAdoptar publicacion, String ruta){
        return "Estimado/a " + destinatario.getNombre() + "\n" + " Nos comunicamos para informarle que hay recomendaciones de mascotas para adoptar relacionadas a su publicacion disponibles!" +
                " para visualizarlas, ingrese a " + ruta;
    }

    public abstract void notificarMascotaEncontrada(Persona destinatario, Persona hallador, Mascota mascotaPerdida);

    public abstract void notificarHayInteresadoEnAdoptar(Persona destinatario, Persona interesado, Mascota mascotaPorSerAdoptada, String ruta);

    public abstract void notificarRecomendaciones(Persona destinatario, PublicacionParaAdoptar publicacion, String ruta);
}
