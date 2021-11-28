package domain.business.notificaciones;
import domain.business.mascota.Mascota;
import domain.business.mascota.MascotaPerdida;
import domain.business.publicaciones.PublicacionParaAdopcion;
import domain.business.users.Persona;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("whatsapp")
public class NotificadorWhatsapp extends Notificacion{

        public NotificadorWhatsapp() {
                super(TipoNotificacion.WHATSAPP);
        }

        @Override
        public TipoNotificacion obtenerCodigoNotificacion() { return TipoNotificacion.WHATSAPP; }

        @Override
        public void notificarMascotaEncontrada(Persona destinatario, Persona hallador, Mascota mascotaPerdida) {
                twillio.enviarWhatsapp(destinatario.getTelefono(), armarMensajeMascotaEncontrada(destinatario,hallador, mascotaPerdida));
        }

        @Override
        public void notificarMascotaEncontradaRescatista(Persona destinatario, Persona duenio, MascotaPerdida mascotaPerdida) {
                twillio.enviarWhatsapp(destinatario.getTelefono(), armarMensajeMascotaEncontradaRescatista(destinatario, duenio, mascotaPerdida));
        }

        @Override
        public void notificarHayInteresadoEnAdoptar(Persona destinatario, Persona interesado, Mascota mascotaPorSerAdoptada) {
                twillio.enviarWhatsapp(destinatario.getTelefono(), armarMensajeHayInteresadoEnAdoptar(destinatario,interesado,mascotaPorSerAdoptada));
                }

        @Override
        public void notificarRecomendaciones(Persona destinatario, PublicacionParaAdopcion publicacion, String ruta) {
                twillio.enviarWhatsapp(destinatario.getTelefono(), armarMensajeRecomendacionesSemanales(destinatario, publicacion, ruta));
                }
        }
