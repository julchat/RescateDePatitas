package domain.security;

public class UserVoluntario implements Rol{
    @Override
    public boolean puedoRegistrarMascota() {
        return false;
    }

    @Override
    public boolean puedoReportarMascotaPerdida() {
        return true;
    }

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
