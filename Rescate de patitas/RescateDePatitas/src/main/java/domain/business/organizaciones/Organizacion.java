package domain.business.organizaciones;

import domain.business.EntidadPersistente;
import domain.business.caracteristicas.Caracteristica;
import domain.business.publicaciones.Pregunta;
import domain.business.users.Administrador;
import domain.business.users.Voluntario;
import domain.business.foto.DimensionEstandar;
import domain.business.foto.Foto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Entity
@Table(name = "organizacion")
public class Organizacion extends EntidadPersistente {

    private String nombreOrganizacion;
    private LocalDate fechaDeCreacion;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Column(name = "caracteristicas admitidas")
    private List<Caracteristica> caracteristicasAdmitidas = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "logo_organizacion")
    private Foto logo;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dimension_organizacion")
    private DimensionEstandar dimensionEstandar;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "preguntas_organizacion")
    private List<Pregunta> preguntasOrganizacion = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "voluntarios")
    private List<Voluntario> voluntarios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "administradores")
    private List<Administrador> administradores = new ArrayList<>();


    // Getters and Setters
    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public List<Caracteristica> getCaracteristicasAdmitidas() {
        return caracteristicasAdmitidas;
    }

    public void agregarCaracteristicaAdmitida(Caracteristica caracteristica) { this.caracteristicasAdmitidas.add(caracteristica); }

    public boolean quitarCaracteristicaAdmitida(Caracteristica nombreCaracteristica) {
        if(this.existeCaracteristica(nombreCaracteristica)){
            this.caracteristicasAdmitidas.remove(this.buscarCaracteristica(nombreCaracteristica));
            return true;
        }
        else {
            return false;
        }
    }

    public Foto getLogo() {
        return logo;
    }

    public void setLogo(Foto logo) {
        this.logo = logo;
    }

    public DimensionEstandar getDimensionEstandar() {
        return dimensionEstandar;
    }

    public void setDimensionEstandar(DimensionEstandar dimensionEstandar) { this.dimensionEstandar = dimensionEstandar; }

    public List<Pregunta> getPreguntasOrganizacion() { return preguntasOrganizacion; }

    public void setPreguntasOrganizacion(List<Pregunta> preguntasOrganizacion) { this.preguntasOrganizacion = preguntasOrganizacion; }

    public List<Voluntario> getVoluntarios() { return voluntarios; }

    public void setVoluntarios(List<Voluntario> voluntarios) { this.voluntarios = voluntarios; }

    public List<Administrador> getAdministradores() { return administradores; }

    public void setAdministradores(List<Administrador> administradores) { this.administradores = administradores; }


    // Constructor
    public Organizacion() {}


    // Metodos
    public boolean aceptaCaracteristica(Caracteristica caracteristicaMascota) {
        if(this.existeCaracteristica(caracteristicaMascota)){
            Caracteristica caracteristica = this.buscarCaracteristica(caracteristicaMascota);
            return caracteristica.equals(caracteristicaMascota);
        }
        else {
            return false;
        }
    }

    private Caracteristica buscarCaracteristica(Caracteristica caracteristicaBuscada) {
        return caracteristicasAdmitidas.stream().filter(caracteristica -> caracteristica.equals(caracteristicaBuscada)).collect(Collectors.toList()).get(0);
    }

    private boolean existeCaracteristica(Caracteristica nombreCaracteristica) {
        return caracteristicasAdmitidas.stream().anyMatch(caracteristica -> caracteristica.equals(nombreCaracteristica));
    }

    public void crearPregunta(String pregunta) {
        Pregunta nuevaPregunta = new Pregunta(pregunta);
        this.preguntasOrganizacion.add(nuevaPregunta);
    }
}
