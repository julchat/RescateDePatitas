package domain.security;

public class User implements Rol{

    @Override
    public boolean puedoAprobarPublicaciones() { return false; }

    @Override
    public boolean puedoCrearAdministradores() { return false; }

    @Override
    public boolean puedoCambiarEstandares() { return false; }
}
