package domain.security;

public interface Rol {
    public boolean puedoAprobarPublicaciones();
    public boolean puedoCrearAdministradores();
    public boolean puedoCambiarEstandares();
}
