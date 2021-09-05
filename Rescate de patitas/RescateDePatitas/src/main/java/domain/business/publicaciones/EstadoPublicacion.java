package domain.business.publicaciones;

import domain.security.Usuario;

public interface EstadoPublicacion {
    void administrarPublicacion();
    boolean esVisible(Usuario usuario);
}
