package persistence.entities;

import domain.business.publicaciones.Estados;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "publicacion")
public class PublicacionDB extends EntidadPersistente {

    @Column(name = "estado_publicacion")
    @Enumerated(EnumType.STRING)
    private Estados estadoPublicacion;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "autor")
    private PersonaDB autor;

    //@Column(name = "Fecha de Publicacion")
    //@Convert(converter = LocalDate.class)
    @Transient
    private LocalDate fecha;


    // Getters and Setters
    public Estados getEstadoPublicacion() { return estadoPublicacion; }

    public void setEstadoPublicacion(Estados estadoPublicacion) { this.estadoPublicacion = estadoPublicacion; }

    public PersonaDB getAutor() { return autor; }

    public void setAutor(PersonaDB autor) { this.autor = autor; }

    public LocalDate getFecha() { return fecha; }

    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
