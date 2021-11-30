package domain.security;

public class Moderador implements Rol{

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
