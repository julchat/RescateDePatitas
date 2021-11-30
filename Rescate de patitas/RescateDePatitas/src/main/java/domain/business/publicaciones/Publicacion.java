package domain.business.publicaciones;

import domain.business.EntidadPersistente;
import domain.business.users.Persona;
import domain.security.Usuario;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "publicacion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Publicacion extends EntidadPersistente {

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estados estado;

    @OneToOne
    @JoinColumn(name = "autor")
    private Persona autor;

    private LocalDate fechaDePublicacion;


    // Getters and Setters
    public Estados getEstado() { return estado; }

    public void setEstado(Estados estado) { this.estado = estado; }

    public Persona getAutor() {
        return autor;
    }

    public void setAutor(Persona autor) {
        this.autor = autor;
    }

    public LocalDate getFechaDePublicacion() { return fechaDePublicacion; }

    public void setFechaDePublicacion(LocalDate fechaDePublicacion) { this.fechaDePublicacion = fechaDePublicacion; }


    // Metodos
    public void crearPublicacion() {
        this.setEstado(Estados.PENDIENTE);
        this.setFechaDePublicacion(LocalDate.now());
    }

}
