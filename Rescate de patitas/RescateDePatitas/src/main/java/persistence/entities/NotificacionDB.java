package persistence.entities;

import domain.business.notificaciones.TipoNotificacion;

import javax.persistence.*;

@Entity
public class NotificacionDB extends EntidadPersistente{

    @ManyToOne
    @JoinColumn(name = "Tipo de Notifcacion", referencedColumnName = "id")
    @Enumerated(EnumType.STRING)
    private TipoNotificacion tipoDeNotificacion;

// Getters and Setters
    public TipoNotificacion getTipoDeNotificacion() { return tipoDeNotificacion; }
}
