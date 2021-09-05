package domain.business.publicaciones;

import domain.business.Voluntario;
import domain.security.Moderador;
import domain.security.Usuario;

public class Pendiente implements EstadoPublicacion{
    @Override
    public void administrarPublicacion() {
    }

    @Override
    public boolean esVisible(Usuario usuario) {
        if(usuario.getRol().getClass() == Moderador.class){
            return true;
        }
        else {
            return false;
        }
    }
}
