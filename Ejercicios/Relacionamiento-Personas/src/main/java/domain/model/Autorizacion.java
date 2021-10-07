package domain.model;

import javax.persistence.*;

@Entity
@Table(name = "autorizacion")
public class Autorizacion extends EntidadPersistente {

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_autorizacion")
    private EstadoAutorizacion estadoAutorizacion;

    // Getters and Setters
    public EstadoAutorizacion getEstadoAutorizacion() { return estadoAutorizacion; }

    public void setEstadoAutorizacion(EstadoAutorizacion estadoAutorizacion) { this.estadoAutorizacion = estadoAutorizacion; }

    public Autorizacion() {
        this.setEstadoAutorizacion(EstadoAutorizacion.EN_ESPERA);
    }

    // Metodos
    public void aceptarAutorizacion() {
        this.setEstadoAutorizacion(EstadoAutorizacion.HABILITADA);
    }

    public void revocarAutorizacion() {
        this.setEstadoAutorizacion(EstadoAutorizacion.INHABILITADA);
    }

    public void revocarAceptacion() {
        this.setEstadoAutorizacion(EstadoAutorizacion.INHABILITADA);
    }
}
