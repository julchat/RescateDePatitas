package domain.security

public class Administrador implements Rol{
    public boolean puedoAprobarPublicaciones(){
        return true;
    }
    public boolean puedoCrearAdministradores(){
        return true;
    }
    public boolean puedoCambiarEstandares(){
        return true;
    }

}