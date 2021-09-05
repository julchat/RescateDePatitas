package domain.security;

public interface Rol {
    boolean puedoRegistrarMascota();
    boolean puedoDarEnAdopcion();
    boolean puedoReportarMascotaPerdida();
    boolean puedoAprobarPublicaciones();
    boolean puedoCrearAdministradores();
    boolean puedoCambiarEstandares();
}
