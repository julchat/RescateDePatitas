package domain.business.organizaciones;

import domain.business.Pregunta;
import domain.business.foto.DimensionEstandar;
import domain.business.caracteristicas.Caracteristica;
import domain.business.caracteristicas.CaracteristicaMascota;
import domain.business.foto.Foto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Organizacion {
    private String nombreOrganizacion;
    private LocalDate fechaDeCreacion;
    private List<Caracteristica> caracteristicasAdmitidas = new ArrayList<>();
    private Foto logo;
    private DimensionEstandar dimensionEstandar;
    private List<HogarDeTransito> hogares = new ArrayList<>();
    private List<Pregunta> preguntasOrganizacion = new ArrayList<>();

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

    public void setCaracteristicasAdmitidas(List<Caracteristica> caracteristicasAdmitidas) {
        this.caracteristicasAdmitidas = caracteristicasAdmitidas;
    }

    public void agregarCaracteristicaAdmitida(Caracteristica caracteristica) {
        this.caracteristicasAdmitidas.add(caracteristica);
    }

    public void quitarCaracteristicaAdmitida(Caracteristica caracteristica) {
        this.caracteristicasAdmitidas.remove(caracteristica);
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

    public void setDimensionEstandar(DimensionEstandar dimensionEstandar) {
        this.dimensionEstandar = dimensionEstandar;
    }

    public List<HogarDeTransito> getHogares() {
        return hogares;
    }

    public void setHogares(List<HogarDeTransito> hogares) {
        this.hogares = hogares;
    }

    public void agregarHogarDeTransito(HogarDeTransito hogar) {
        this.hogares.add(hogar);
    }


    // Constructor
    public Organizacion() {}

    public Organizacion(String nombreOrganizacion, LocalDate fechaDeCreacion, List<Caracteristica> caracteristicasAdmitidas, Foto logo, DimensionEstandar dimensionEstandar, List<HogarDeTransito> hogares) {
        this.nombreOrganizacion = nombreOrganizacion;
        this.fechaDeCreacion = fechaDeCreacion;
        this.caracteristicasAdmitidas = caracteristicasAdmitidas;
        this.logo = logo;
        this.dimensionEstandar = dimensionEstandar;
        this.hogares = hogares;
    }


    // Metodos
    public void agregoSiAceptaCaracteristica(CaracteristicaMascota unaCaracteristica, List<CaracteristicaMascota> caracteristicasValidas ){
        if(this.aceptoCaracteristica(unaCaracteristica)) {
            caracteristicasValidas.add(unaCaracteristica);
        }
    }

    public boolean aceptoCaracteristica(CaracteristicaMascota unaCaracteristica) {
        return caracteristicasAdmitidas.contains(unaCaracteristica.getNombreCaracteristica());
    }

    public void crearPregunta(String pregunta) {
        Pregunta nuevaPregunta = new Pregunta(pregunta);
        this.preguntasOrganizacion.add(nuevaPregunta);
    }
}
