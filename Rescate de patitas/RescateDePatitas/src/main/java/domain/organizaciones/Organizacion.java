package domain.organizaciones;

import domain.business.DimensionEstandar;
import domain.business.caracteristicas.Caracteristica;
import domain.business.caracteristicas.CaracteristicaConValor;
import domain.business.foto.Foto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Organizacion {
    private String nombreOrganizacion;
    private LocalDate fechaDeCreacion;
    private List<Caracteristica> caracteristicasAdmitidas;
    private Foto logo;
    private DimensionEstandar dimensionEstandar;
    private List<HogarDeTransito> hogares;

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


    public void agregoSiAceptaCaracteristica(CaracteristicaConValor unaCaracteristica, List<CaracteristicaConValor> caracteristicasValidas ){
        if(this.aceptoCaracteristica(unaCaracteristica)) {
            caracteristicasValidas.add(unaCaracteristica);
        }
    }

    public boolean aceptoCaracteristica(CaracteristicaConValor unaCaracteristica) {
        return caracteristicasAdmitidas.contains(unaCaracteristica.getNombreCaracteristica());
    }
}
