package domain.security;

public class Administrador implements Rol {
    @Override
    public boolean puedoRegistrarMascota() { return false; }

    @Override
    public boolean puedoReportarMascotaPerdida() { return true; }

    @Override
    public boolean puedoAprobarPublicaciones() { return true; }

    @Override
    public boolean puedoCrearAdministradores() {
        return true;
    }

    @Override
    public boolean puedoCambiarEstandares() {
        return true;
    }
}