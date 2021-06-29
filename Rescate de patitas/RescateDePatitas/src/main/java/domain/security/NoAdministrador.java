package domain.security;

public class NoAdministrador implements Rol{
    public boolean puedoAprobarPublicaciones(){
        return false;
    }
    public boolean puedoCrearAdministradores(){
        return false;
    }
    public boolean puedoCambiarEstandares(){
        return false;
    }
}