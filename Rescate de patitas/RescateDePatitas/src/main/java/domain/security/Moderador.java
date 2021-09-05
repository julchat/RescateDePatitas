package domain.security;

public class Moderador implements Rol{
    @Override
    public boolean puedoRegistrarMascota() { return false; }

    @Override
    public boolean puedoDarEnAdopcion() { return false; }

    @Override
    public boolean puedoReportarMascotaPerdida() { return true; }

    @Override
    public boolean puedoAprobarPublicaciones() { return true; }

    @Override
    public boolean puedoCrearAdministradores() {
        return false;
    }

    @Override
    public boolean puedoCambiarEstandares() {
        return false;
    }
}
