package database.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizacion")
public class OrganizacionDB extends EntidadPersistente {

    private String nombreOrganizacion;
    private LocalDate fechaDeCreacion;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "caracteristicas admitidas")
    private List<CaracteristicaDB> caracteristicasAdmitidas = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "logo_organizacion")
    private FotoDB logo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dimension_organizacion")
    private DimensionesDB dimensionEstandar;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "preguntas_organizacion")
    private List<PreguntaDB> preguntasOrganizacion = new ArrayList<>();


// Getters and Setters
    public String getNombreOrganizacion() { return nombreOrganizacion; }

    public void setNombreOrganizacion(String nombreOrganizacion) { this.nombreOrganizacion = nombreOrganizacion; }

    public LocalDate getFechaDeCreacion() { return fechaDeCreacion; }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) { this.fechaDeCreacion = fechaDeCreacion; }

    public List<CaracteristicaDB> getCaracteristicasAdmitidas() { return caracteristicasAdmitidas; }

    public void setCaracteristicasAdmitidas(List<CaracteristicaDB> caracteristicasAdmitidas) { this.caracteristicasAdmitidas = caracteristicasAdmitidas; }

    public FotoDB getLogo() { return logo; }

    public void setLogo(FotoDB logo) { this.logo = logo; }

    public DimensionesDB getDimensionEstandar() { return dimensionEstandar; }

    public void setDimensionEstandar(DimensionesDB dimensionEstandar) { this.dimensionEstandar = dimensionEstandar; }

    public List<PreguntaDB> getPreguntasOrganizacion() { return preguntasOrganizacion; }

    public void setPreguntasOrganizacion(List<PreguntaDB> preguntasOrganizacion) { this.preguntasOrganizacion = preguntasOrganizacion; }
}