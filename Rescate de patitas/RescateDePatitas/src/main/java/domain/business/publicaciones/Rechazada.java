package domain.business.publicaciones;

import domain.security.Usuario;

public class Rechazada implements EstadoPublicacion{
    @Override
    public void administrarPublicacion() {

    }
    // TODO solamente se notifica al autor que se rechazo por X motivo
    @Override
    public boolean esVisible(Usuario usuario) {
        // TODO: avisar al usuario que se rechazo
        //usuario.notify();
        return false;
    }
}
