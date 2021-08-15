/*package domain.notificaciones;
import domain.business.Mascota;
import domain.business.Persona;
import domain.business.publicaciones.BusquedaMascotaIdeal;

import java.io.IOException;

public class NotificadorEmail extends Notificacion{
    @Override
    public void notificarMascotaEncontrada(Persona destinatario, Persona hallador, Mascota mascotaPerdida, String ruta){
            twillio.enviarEmail(destinatario.getTelefono(),"Tu mascota ha sido encontrada",armarMensajeMascotaEncontrada(destinatario,hallador, mascotaPerdida,ruta));
    }

    @Override
    public void notificarHayInteresadoEnAdoptar(Persona destinatario, Persona interesado, Mascota mascotaPorSerAdoptada, String ruta) {
            twillio.enviarEmail(destinatario.getTelefono(), "Hay un interesado en adoptar tu mascota", armarMensajeHayInteresadoEnAdoptar(destinatario, interesado, mascotaPorSerAdoptada, ruta));
    }

    @Override
    public void notificarRecomendaciones(Persona destinatario, BusquedaMascotaIdeal publicacion, String ruta) {
            twillio.enviarEmail(destinatario.getTelefono(), "Revisa estas recomendaciones para adoptar!", armarMensajeRecomendacionesSemanales(destinatario, publicacion, ruta));
    }
}*/
