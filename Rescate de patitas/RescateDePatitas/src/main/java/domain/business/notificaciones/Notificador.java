package domain.business.notificaciones;

import domain.business.mascota.Mascota;
import domain.business.users.Duenio;
import domain.business.users.Rescatista;

public class Notificador {
    private static Notificador notificador = null;


    public static Notificador getInstance() {
        if(notificador == null) {
            notificador = new Notificador();
        }
        return notificador;
    }

    public void notificarDuenio(Duenio duenio, Rescatista rescatista, Mascota mascotaPerdida) {
        // Notifica al Dueño de la Mascota
        duenio.getFormasDeNotificacion().forEach(notificacion -> notificacion.notificarMascotaEncontrada(duenio, rescatista, mascotaPerdida));
        // Notifica a cada uno de los Contactos que haya agregado la persona
        if(!duenio.getContactos().isEmpty()) {
            duenio.getContactos().forEach(contacto -> contacto.getFormasDeNotificacion().forEach(notificacion -> notificacion.notificarMascotaEncontrada(duenio, rescatista, mascotaPerdida)));
        }
    }
}
