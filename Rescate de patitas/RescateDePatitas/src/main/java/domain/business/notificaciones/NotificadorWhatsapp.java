package domain.business.notificaciones;
import domain.business.mascota.Mascota;
import domain.business.users.Persona;
import domain.business.publicaciones.PublicacionParaAdoptar;

public class NotificadorWhatsapp extends Notificacion{
        @Override
        public TipoNotificacion obtenerCodigoNotificacion() { return TipoNotificacion.WHATSAPP; }

        @Override
public void notificarMascotaEncontrada(Persona destinatario, Persona hallador, Mascota mascotaPerdida) {
        twillio.enviarWhatsapp(destinatario.getTelefono(), armarMensajeMascotaEncontrada(destinatario,hallador, mascotaPerdida));
        }

@Override
public void notificarHayInteresadoEnAdoptar(Persona destinatario, Persona interesado, Mascota mascotaPorSerAdoptada, String ruta) {
        twillio.enviarWhatsapp(destinatario.getTelefono(),armarMensajeHayInteresadoEnAdoptar(destinatario,interesado,mascotaPorSerAdoptada,ruta));
        }

@Override
public void notificarRecomendaciones(Persona destinatario, PublicacionParaAdoptar publicacion, String ruta) {
        twillio.enviarWhatsapp(destinatario.getTelefono(),armarMensajeRecomendacionesSemanales(destinatario, publicacion, ruta));
        }
}
