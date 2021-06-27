package domain.notificaciones;

import domain.business.Persona;

public class NotificadorSms implements Notificacion{
    @Override
    public void notificar(Persona persona) {
        // Todo logica para notificar a una persona por sms (a su numero de telefono, siempre que sea un celular)
    }
}
