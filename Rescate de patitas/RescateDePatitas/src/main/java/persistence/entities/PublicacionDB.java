package persistence.entities;

import domain.business.publicaciones.EstadoPublicacion;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Publicaciones")
public class PublicacionDB extends EntidadPersistente {

    @Column(name = "Estado Publicacion")
    @Convert(converter = EstadoPublicacion.class)
    private EstadoPublicacion estadoPublicacion;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Autor")
    private PersonaDB autor;

    @Column(name = "Fecha de Publicacion")
    @Convert(converter = LocalDate.class)
    private LocalDate fecha;


// Getters and Setters
    public EstadoPublicacion getEstadoPublicacion() { return estadoPublicacion; }

    public void setEstadoPublicacion(EstadoPublicacion estadoPublicacion) { this.estadoPublicacion = estadoPublicacion; }

    public PersonaDB getAutor() { return autor; }

    public void setAutor(PersonaDB autor) { this.autor = autor; }

    public LocalDate getFecha() { return fecha; }

    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
