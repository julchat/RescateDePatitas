package domain.notificaciones;
import domain.business.Mascota;
import domain.business.Persona;
import domain.business.publicaciones.BusquedaMascotaIdeal;

public class NotificadorEmail extends Notificacion{
    @Override
    public void notificarMascotaEncontrada(Persona destinatario, Persona hallador, Mascota mascotaPerdida, String ruta){

    }

    @Override
    public void notificarHayInteresadoEnAdoptar(Persona destinatario, Persona interesado, Mascota mascotaPorSerAdoptada, String ruta) {

    }

    @Override
    public void notificarRecomendaciones(Persona destinatario, BusquedaMascotaIdeal publicacion, String ruta) {

    }
}
