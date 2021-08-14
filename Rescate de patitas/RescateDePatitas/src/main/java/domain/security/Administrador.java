package domain.security;

public class Administrador implements Rol {
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