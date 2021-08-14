package domain.business.publicaciones;

public class Aprobada implements EstadoPublicacion{
    @Override
    public void administrarPublicacion() {

    }

    @Override
    public boolean esVisible() {
        return true;
    }
}
