package domain.business.publicaciones;

public class Pendiente implements EstadoPublicacion{
    @Override
    public void administrarPublicacion() {
    }

    @Override
    public boolean esVisible() {
        // TODO depende del rol, si es Moderador puede verla, si es Persona (Dueño, Rescatista) no
        return false;
    }
}
