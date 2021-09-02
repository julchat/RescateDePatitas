package domain.business.notificaciones;
import domain.business.Mascota;
import domain.business.Persona;
import domain.business.publicaciones.BusquedaMascotaIdeal;

public class NotificadorWhatsapp extends Notificacion{
        @Override
        public int obtenerCodigoNotificacion() { return 3; }

        @Override
public void notificarMascotaEncontrada(Persona destinatario, Persona hallador, Mascota mascotaPerdida, String ruta) {
        twillio.enviarWhatsapp(destinatario.getTelefono(),armarMensajeMascotaEncontrada(destinatario,hallador, mascotaPerdida,ruta));
        }

@Override
public void notificarHayInteresadoEnAdoptar(Persona destinatario, Persona interesado, Mascota mascotaPorSerAdoptada, String ruta) {
        twillio.enviarWhatsapp(destinatario.getTelefono(),armarMensajeHayInteresadoEnAdoptar(destinatario,interesado,mascotaPorSerAdoptada,ruta));
        }

@Override
public void notificarRecomendaciones(Persona destinatario, BusquedaMascotaIdeal publicacion, String ruta) {
        twillio.enviarWhatsapp(destinatario.getTelefono(),armarMensajeRecomendacionesSemanales(destinatario, publicacion, ruta));
        }
}
