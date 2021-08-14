package domain.business.publicaciones;

public class Rechazada implements EstadoPublicacion{
    @Override
    public void administrarPublicacion() {

    }
    // TODO solamente se notifica al autor que se rechazo por X motivo
    @Override
    public boolean esVisible() {
        return false;
    }
}
