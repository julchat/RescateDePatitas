package domain.security;

public interface Rol {
    abstract boolean puedoAprobarPublicaciones();
    abstract boolean puedoCrearAdministradores();
    abstract boolean puedoCambiarEstandares();
}
