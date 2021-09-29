package domain.security;

public interface Rol {
    abstract boolean puedoRegistrarMascota();
    abstract boolean puedoDarEnAdopcion();
    abstract boolean puedoReportarMascotaPerdida();
    abstract boolean puedoAprobarPublicaciones();
    abstract boolean puedoCrearAdministradores();
    abstract boolean puedoCambiarEstandares();
}
