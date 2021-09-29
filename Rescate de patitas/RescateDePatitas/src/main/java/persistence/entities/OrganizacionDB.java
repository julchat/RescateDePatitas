package persistence.entities;

import domain.business.Pregunta;
import domain.business.foto.DimensionEstandar;
import domain.business.foto.Foto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizacion")
public class OrganizacionDB extends EntidadPersistente {

    @Column(name = "nombre_organizacion")
    private String nombreOrganizacion;

    @Column(name = "fecha_de_creacion", columnDefinition = "DATE")
    private LocalDate fechaDeCreacion;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Column(name = "caracteristicas admitidas")
    private List<CaracteristicaDB> caracteristicasAdmitidas = new ArrayList<>();

    //@OneToOne(cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "Logo")
    @Transient
    private Foto logo;

    //@Column(name = "Dimension estandar")
    //@Convert(converter = DimensionEstandar.class)
    @Transient
    private DimensionEstandar dimensionEstandar;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hogares")
    private List<HogarDeTransitoDB> hogares = new ArrayList<>();

    //@OneToMany(cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "Preguntas")
    @Transient
    private List<Pregunta> preguntasOrganizacion = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "voluntarios")
    private List<PersonaDB> voluntarios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "administradores")
    private List<PersonaDB> administradores = new ArrayList<>();

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

    public List<HogarDeTransitoDB> getHogares() { return hogares; }

    public void setHogares(List<HogarDeTransitoDB> hogares) { this.hogares = hogares; }

    public List<Pregunta> getPreguntasOrganizacion() { return preguntasOrganizacion; }

    public void setPreguntasOrganizacion(List<Pregunta> preguntasOrganizacion) { this.preguntasOrganizacion = preguntasOrganizacion; }

}