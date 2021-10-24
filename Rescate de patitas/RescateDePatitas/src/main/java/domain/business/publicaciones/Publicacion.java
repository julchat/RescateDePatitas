package domain.business.publicaciones;

import domain.business.EntidadPersistente;
import domain.business.users.Persona;
import domain.security.Usuario;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "publicacion")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public abstract class Publicacion extends EntidadPersistente {

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estados estado;

    @Transient
    private EstadoPublicacion estadoPublicacion;

    @OneToOne
    @JoinColumn(name = "autor")
    private Persona autor;

    private LocalDate fechaDePublicacion;
    private String ruta;


    // Getters and Setters
    public Estados getEstado() { return estado; }

    public void setEstado(Estados estado) { this.estado = estado; }

    public EstadoPublicacion getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(EstadoPublicacion estadoPublicacion) { this.estadoPublicacion = estadoPublicacion; }

    public Persona getAutor() {
        return autor;
    }

    public void setAutor(Persona autor) {
        this.autor = autor;
    }

    public LocalDate getFechaDePublicacion() { return fechaDePublicacion; }

    public void setFechaDePublicacion(LocalDate fechaDePublicacion) { this.fechaDePublicacion = fechaDePublicacion; }

    public String getRuta() { return ruta; }

    public void setRuta(String ruta) { this.ruta = ruta; }


    // Metodos
    public void crearPublicacion(EstadoPublicacion estadoPublicacion) {
        this.setEstadoPublicacion(estadoPublicacion);
        this.setFechaDePublicacion(LocalDate.now());
        this.setRuta(ruta);
    }

    public void cambiarEstado(EstadoPublicacion nuevoEstado) {
        this.estadoPublicacion = nuevoEstado;
    }

    public boolean esVisible(Usuario usuario) {
        return this.estadoPublicacion.esVisible(usuario);
    }

    public abstract void mostrarPublicacion();

}
