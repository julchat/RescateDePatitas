package domain.security;

public class Admin implements Rol {

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