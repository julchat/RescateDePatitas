package domain.security;

public class Admin implements Rol {
    @Override
    public boolean puedoRegistrarMascota() { return false; }

    @Override
    public boolean puedoDarEnAdopcion() { return false; }

    @Override
    public boolean puedoReportarMascotaPerdida() { return true; }

    @Override
    public boolean puedoAprobarPublicaciones() { return false; }

    @Override
    public boolean puedoCrearAdministradores() {
        return true;
    }

    @Override
    public boolean puedoCambiarEstandares() {
        return true;
    }
}