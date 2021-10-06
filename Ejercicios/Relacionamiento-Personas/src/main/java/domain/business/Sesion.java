package domain.business;

public class Sesion {

    //SchedulerCierreSesion
    private EstadoSesion estadoSesion;

    // Getters and Setters
    public EstadoSesion getEstadoSesion() { return estadoSesion; }

    public void setEstadoSesion(EstadoSesion estadoSesion) { this.estadoSesion = estadoSesion; }


    // Metodos
    public Sesion() {
        this.setEstadoSesion(EstadoSesion.INICIADA);
    }

    public void validarSesionAbierta() {

    }

    public void cerrarSesion() {
        this.setEstadoSesion(EstadoSesion.CERRADA);
    }
}
