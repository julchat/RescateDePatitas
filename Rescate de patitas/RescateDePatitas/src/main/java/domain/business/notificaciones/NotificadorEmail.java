package domain.business.notificaciones;
import domain.business.mascota.Mascota;
import domain.business.users.Persona;
import domain.business.publicaciones.PublicacionParaAdoptar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("email")
public class NotificadorEmail extends Notificacion{

    public NotificadorEmail() {
        super(TipoNotificacion.EMAIL);
    }

    @Override
    public TipoNotificacion obtenerCodigoNotificacion() { return TipoNotificacion.EMAIL; }

    @Override
    public void notificarMascotaEncontrada(Persona destinatario, Persona hallador, Mascota mascotaPerdida){
            mailSender.enviarEmail(destinatario.getEmail(),"Tu mascota ha sido encontrada", armarMensajeMascotaEncontrada(destinatario,hallador, mascotaPerdida));
    }

    @Override
    public void notificarHayInteresadoEnAdoptar(Persona destinatario, Persona interesado, Mascota mascotaPorSerAdoptada, String ruta) {
           mailSender.enviarEmail(destinatario.getEmail(), "Hay un interesado en adoptar tu mascota", armarMensajeHayInteresadoEnAdoptar(destinatario, interesado, mascotaPorSerAdoptada, ruta));
    }

    @Override
    public void notificarRecomendaciones(Persona destinatario, PublicacionParaAdoptar publicacion, String ruta) {
            mailSender.enviarEmail(destinatario.getEmail(), "Revisa estas recomendaciones para adoptar!", armarMensajeRecomendacionesSemanales(destinatario, publicacion, ruta));
    }
}
