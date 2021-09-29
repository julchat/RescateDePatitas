package persistence.entities;

import domain.business.Contacto;
import domain.business.TipoDoc;
import domain.business.notificaciones.Notificacion;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona")
public class PersonaDB extends EntidadPersistente {

    private String nombre;
    private String apellido;
    private LocalDate fechaDeNacimiento;

    @Enumerated(EnumType.STRING)
    private TipoDoc tipoDocumento;

    private int numeroDocumento;
    private String telefono;
    private String email;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "notificaciones")
    private List<NotificacionDB> formasDeNotificacion = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contactos")
    private List<ContactoDB> contactos = new ArrayList<>();

    private boolean suscripto;


}
