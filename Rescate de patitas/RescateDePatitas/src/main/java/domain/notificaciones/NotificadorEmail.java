package domain.notificaciones;

import domain.business.Persona;

public class NotificadorEmail implements Notificacion{

    @Override
    public void notificar(Persona persona) {
        // Todo logica para notificar a una persona via mail
    }
}
