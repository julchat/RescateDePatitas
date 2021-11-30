package domain.business.notificaciones;
import domain.business.mascota.Mascota;
import domain.business.mascota.MascotaPerdida;
import domain.business.publicaciones.PublicacionParaAdopcion;
import domain.business.users.Persona;

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
    public void notificarMascotaEncontradaRescatista(Persona destinatario, Persona duenio, MascotaPerdida mascotaPerdida) {
        mailSender.enviarEmail(destinatario.getEmail(),"Un Dueño encontró a su mascota", armarMensajeMascotaEncontradaRescatista(destinatario, duenio, mascotaPerdida));
    }

    @Override
    public void notificarHayInteresadoEnAdoptar(Persona destinatario, Persona interesado, Mascota mascotaPorSerAdoptada) {
           mailSender.enviarEmail(destinatario.getEmail(), "Hay un interesado en adoptar tu mascota", armarMensajeHayInteresadoEnAdoptar(destinatario, interesado, mascotaPorSerAdoptada));
    }

    @Override
    public void notificarRecomendaciones(Persona destinatario, PublicacionParaAdopcion publicacion, String ruta) {
            mailSender.enviarEmail(destinatario.getEmail(), "Revisa estas recomendaciones para adoptar!", armarMensajeRecomendacionesSemanales(destinatario, publicacion, ruta));
    }
}
