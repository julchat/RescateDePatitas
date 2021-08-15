package domain.security;

public interface Rol {
    public boolean puedoRegistrarMascota();
    public boolean puedoReportarMascotaPerdida();
    public boolean puedoAprobarPublicaciones();
    public boolean puedoCrearAdministradores();
    public boolean puedoCambiarEstandares();
}
