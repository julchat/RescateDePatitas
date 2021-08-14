package domain.business.publicaciones;

public class Cerrada implements EstadoPublicacion{
    @Override
    public void administrarPublicacion() {

    }

    @Override
    public boolean esVisible() {
        return false;
    }
}
