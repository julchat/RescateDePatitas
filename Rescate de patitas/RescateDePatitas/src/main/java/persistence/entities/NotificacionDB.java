package persistence.entities;

import domain.business.notificaciones.TipoNotificacion;

import javax.persistence.*;

@Entity
@Table(name = "notificacion")
public class NotificacionDB extends EntidadPersistente{

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_notificacion")
    private TipoNotificacion tipoDeNotificacion;

    // Getters and Setters
    public TipoNotificacion getTipoDeNotificacion() { return tipoDeNotificacion; }

    public void setTipoDeNotificacion(TipoNotificacion tipoDeNotificacion) { this.tipoDeNotificacion = tipoDeNotificacion; }
}