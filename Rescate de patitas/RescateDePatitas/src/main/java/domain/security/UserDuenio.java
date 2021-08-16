package domain.security;

public class UserDuenio implements Rol{
    @Override
    public boolean puedoRegistrarMascota() { return true; }

    @Override
    public boolean puedoReportarMascotaPerdida() { return true; }

    @Override
    public boolean puedoAprobarPublicaciones() { return false; }

    @Override
    public boolean puedoCrearAdministradores() { return false; }

    @Override
    public boolean puedoCambiarEstandares() { return false; }
}
