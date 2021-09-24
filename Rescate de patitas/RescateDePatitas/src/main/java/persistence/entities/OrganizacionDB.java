package persistence.entities;

import domain.business.Pregunta;
import domain.business.foto.DimensionEstandar;
import domain.business.foto.Foto;

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

    @OneToMany(cascade = CascadeType.PERSIST)
    @Column(name = "Caracteristicas Admitidas")
    private List<CaracteristicaDB> caracteristicasAdmitidas = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Logo")
    private Foto logo;

    @Column(name = "Dimension estandar")
    @Convert(converter = DimensionEstandar.class)
    private DimensionEstandar dimensionEstandar;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Preguntas")
    private List<Pregunta> preguntasOrganizacion = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Voluntarios")
    private List<VoluntarioDB> voluntarios = new ArrayList<VoluntarioDB>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Administradores")
    private List<AdminDB> administradores = new ArrayList<AdminDB>();

// Getters and Setters
    public String getNombreOrganizacion() { return nombreOrganizacion; }

    public void setNombreOrganizacion(String nombreOrganizacion) { this.nombreOrganizacion = nombreOrganizacion; }

    public LocalDate getFechaDeCreacion() { return fechaDeCreacion; }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) { this.fechaDeCreacion = fechaDeCreacion; }

    public List<CaracteristicaDB> getCaracteristicasAdmitidas() { return caracteristicasAdmitidas; }

    public void setCaracteristicasAdmitidas(List<CaracteristicaDB> caracteristicasAdmitidas) { this.caracteristicasAdmitidas = caracteristicasAdmitidas; }

    public Foto getLogo() { return logo; }

    public void setLogo(Foto logo) { this.logo = logo; }

    public DimensionEstandar getDimensionEstandar() { return dimensionEstandar; }

    public void setDimensionEstandar(DimensionEstandar dimensionEstandar) { this.dimensionEstandar = dimensionEstandar; }

    public List<Pregunta> getPreguntasOrganizacion() { return preguntasOrganizacion; }

    public void setPreguntasOrganizacion(List<Pregunta> preguntasOrganizacion) { this.preguntasOrganizacion = preguntasOrganizacion; }

    public List<VoluntarioDB> getVoluntarios() { return voluntarios; }

    public void setVoluntarios(List<VoluntarioDB> voluntarios) { this.voluntarios = voluntarios; }

    public List<AdminDB> getAdministradores() { return administradores; }

    public void setAdministradores(List<AdminDB> administradores) { this.administradores = administradores; }
}
