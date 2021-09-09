package domain.business.organizaciones;

import domain.business.Pregunta;
import domain.business.foto.DimensionEstandar;
import domain.business.caracteristicas.Caracteristica;
import domain.business.caracteristicas.CaracteristicaMascota;
import domain.business.foto.Foto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void agregarCaracteristicaAdmitida(Caracteristica caracteristica) {
        this.caracteristicasAdmitidas.add(caracteristica);
    }

    public boolean quitarCaracteristicaAdmitida(String nombreCaracteristica) {
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


    // Metodos
    public boolean aceptaCaracteristica(CaracteristicaMascota caracteristicaMascota) {
        if(this.existeCaracteristica(caracteristicaMascota.getNombreCaracteristica())){
            Caracteristica caracteristica = this.buscarCaracteristica(caracteristicaMascota.getNombreCaracteristica());
            return caracteristica.getOpcionesValidas().contains(caracteristicaMascota.getValorElegido());
        }
        else {
            return false;
        }
    }

    private Caracteristica buscarCaracteristica(String nombreCaracteristica) {
        return caracteristicasAdmitidas.stream().filter(caracteristica -> caracteristica.getNombre().equals(nombreCaracteristica)).collect(Collectors.toList()).get(0);
    }

    private boolean existeCaracteristica(String nombreCaracteristica) {
        return caracteristicasAdmitidas.stream().anyMatch(caracteristica -> caracteristica.getNombre().equals(nombreCaracteristica));
    }


    public void crearPregunta(String pregunta) {
        Pregunta nuevaPregunta = new Pregunta(pregunta);
        this.preguntasOrganizacion.add(nuevaPregunta);
    }
}
