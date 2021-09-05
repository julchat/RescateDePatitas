package domain.business.publicaciones;

import domain.security.Usuario;

public class Aprobada implements EstadoPublicacion{
    @Override
    public void administrarPublicacion() {

    }

    @Override
    public boolean esVisible(Usuario usuario) {
        return true;
    }
}
