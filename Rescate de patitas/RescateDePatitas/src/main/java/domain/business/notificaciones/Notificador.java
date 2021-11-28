package domain.business.notificaciones;

import domain.business.mascota.Mascota;
import domain.business.mascota.MascotaPerdida;
import domain.business.users.Persona;

public class Notificador {
    private static Notificador notificador = null;


    public static Notificador getInstance() {
        if(notificador == null) {
            notificador = new Notificador();
        }
        return notificador;
    }

    public void notificarDuenio(Persona duenio, Persona rescatista, Mascota mascotaPerdida) {
        duenio.getFormasDeNotificacion().forEach(notificacion -> notificacion.notificarMascotaEncontrada(duenio, rescatista, mascotaPerdida));
        if(!duenio.getContactos().isEmpty()) {
            duenio.getContactos().forEach(contacto -> contacto.getFormasDeNotificacion().forEach(notificacion -> notificacion.notificarMascotaEncontrada(duenio, rescatista, mascotaPerdida)));
        }
    }

    public void notificarPorAdopcion(Persona duenio, Persona persona, Mascota mascotaElegida) {
        duenio.getFormasDeNotificacion().forEach(notificacion -> notificacion.notificarHayInteresadoEnAdoptar(duenio, persona, mascotaElegida));
        if(!duenio.getContactos().isEmpty()) {
            duenio.getContactos().forEach(contacto -> contacto.getFormasDeNotificacion().forEach(notificacion -> notificacion.notificarHayInteresadoEnAdoptar(duenio, persona, mascotaElegida)));
        }
    }

    public void notificarRescatista(Persona rescatista, Persona persona, MascotaPerdida mascotaPerdida) {
        rescatista.getFormasDeNotificacion().forEach(notificacion -> notificacion.notificarMascotaEncontradaRescatista(rescatista, persona, mascotaPerdida));
    }
}
