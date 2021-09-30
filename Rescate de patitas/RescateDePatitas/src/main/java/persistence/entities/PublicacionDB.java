package persistence.entities;

import domain.business.publicaciones.Estados;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "publicacion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public class PublicacionDB extends EntidadPersistente {

    @Column(name = "estado_publicacion")
    @Enumerated(EnumType.STRING)
    private Estados estadoPublicacion;

    @OneToOne
    @JoinColumn(name = "persona_id")
    private PersonaDB autor;

    private LocalDate fechaDePublicacion;


// Getters and Setters
    public Estados getEstadoPublicacion() { return estadoPublicacion; }

    public void setEstadoPublicacion(Estados estadoPublicacion) { this.estadoPublicacion = estadoPublicacion; }

    public PersonaDB getAutor() { return autor; }

    public void setAutor(PersonaDB autor) { this.autor = autor; }

    public LocalDate getFechaDePublicacion() { return fechaDePublicacion; }

    public void setFechaDePublicacion(LocalDate fecha) { this.fechaDePublicacion = fecha; }
}
