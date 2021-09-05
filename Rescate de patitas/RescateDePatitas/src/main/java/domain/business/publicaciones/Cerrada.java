package domain.business.publicaciones;

import domain.security.Usuario;

public class Cerrada implements EstadoPublicacion{
    @Override
    public void administrarPublicacion() {

    }

    @Override
    public boolean esVisible(Usuario usuario) {
        // TODO: en este caso tendria que eliminarse de la BD de las Publicaciones?
        return false;
    }
}
