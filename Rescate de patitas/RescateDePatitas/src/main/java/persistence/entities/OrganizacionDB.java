package persistence.entities;

import domain.business.Pregunta;
import domain.business.caracteristicas.Caracteristica;
import domain.business.foto.DimensionEstandar;
import domain.business.foto.Foto;
import domain.business.organizaciones.HogarDeTransito;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Organizacion")
public class OrganizacionDB extends EntidadPersistente {

    @Column(name = "Nombre")
    private String nombreOrganizacion;

    @Column(name = "Fecha de Creacion", columnDefinition = "DATE")
    private LocalDate fechaDeCreacion;

    @OneToMany(mappedBy = "Organizacion")
    @Column(name = "Caracteristicas admitidas")
    private List<Caracteristica> caracteristicasAdmitidas = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "Foto ID")
    private Foto logo;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "Dimension estandar")
    private DimensionEstandar dimensionEstandar;

    @OneToMany(mappedBy = "Organizacion")
    @JoinColumn(name = "Hogares")
    private List<HogarDeTransito> hogares = new ArrayList<>();

    @OneToMany(mappedBy = "Organizacion")
    @JoinColumn(name = "Preguntas")
    private List<Pregunta> preguntasOrganizacion = new ArrayList<>();

    @OneToMany(mappedBy = "Organizacion")
    @JoinColumn(name = "Voluntarios")
    private List<VoluntarioDB> voluntarios;

    @OneToMany(mappedBy = "Organizacion")
    @JoinColumn(name = "Administradores")
    private List<AdminDB> administradores;

// Getters and Setters
    public String getNombreOrganizacion() { return nombreOrganizacion; }

    public void setNombreOrganizacion(String nombreOrganizacion) { this.nombreOrganizacion = nombreOrganizacion; }

    public LocalDate getFechaDeCreacion() { return fechaDeCreacion; }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) { this.fechaDeCreacion = fechaDeCreacion; }

    public List<Caracteristica> getCaracteristicasAdmitidas() { return caracteristicasAdmitidas; }

    public void setCaracteristicasAdmitidas(List<Caracteristica> caracteristicasAdmitidas) { this.caracteristicasAdmitidas = caracteristicasAdmitidas; }

    public Foto getLogo() { return logo; }

    public void setLogo(Foto logo) { this.logo = logo; }

    public DimensionEstandar getDimensionEstandar() { return dimensionEstandar; }

    public void setDimensionEstandar(DimensionEstandar dimensionEstandar) { this.dimensionEstandar = dimensionEstandar; }

    public List<HogarDeTransito> getHogares() { return hogares; }

    public void setHogares(List<HogarDeTransito> hogares) { this.hogares = hogares; }

    public List<Pregunta> getPreguntasOrganizacion() { return preguntasOrganizacion; }

    public void setPreguntasOrganizacion(List<Pregunta> preguntasOrganizacion) { this.preguntasOrganizacion = preguntasOrganizacion; }

    public List<VoluntarioDB> getVoluntarios() { return voluntarios; }

    public void setVoluntarios(List<VoluntarioDB> voluntarios) { this.voluntarios = voluntarios; }

    public List<AdminDB> getAdministradores() { return administradores; }

    public void setAdministradores(List<AdminDB> administradores) { this.administradores = administradores; }
}
