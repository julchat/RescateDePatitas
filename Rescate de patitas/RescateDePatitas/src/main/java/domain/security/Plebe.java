package domain.security;

public class Plebe implements Rol{
    @Override
    public boolean puedoAprobarPublicaciones() { return false; }

    @Override
    public boolean puedoCrearAdministradores() { return false; }

    @Override
    public boolean puedoCambiarEstandares() { return false; }
}
