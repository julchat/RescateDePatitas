package domain.business.organizaciones;

import domain.business.publicaciones.Pregunta;
import domain.business.users.Voluntario;
import domain.business.foto.DimensionEstandar;
import domain.business.foto.Foto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Organizacion {
    private String nombreOrganizacion;
    private LocalDate fechaDeCreacion;
    private List<String> caracteristicasAdmitidas = new ArrayList<>();
    private Foto logo;
    private DimensionEstandar dimensionEstandar;
    private List<Pregunta> preguntasOrganizacion = new ArrayList<>();
    private List<Voluntario> voluntarios = new ArrayList<>();

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

    public List<String> getCaracteristicasAdmitidas() {
        return caracteristicasAdmitidas;
    }

    public void agregarCaracteristicaAdmitida(String caracteristica) { this.caracteristicasAdmitidas.add(caracteristica); }

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

    public void setDimensionEstandar(DimensionEstandar dimensionEstandar) { this.dimensionEstandar = dimensionEstandar; }

    public List<Voluntario> getVoluntarios() { return voluntarios; }

    public void setVoluntarios(List<Voluntario> voluntarios) { this.voluntarios = voluntarios; }

    public List<Pregunta> getPreguntasOrganizacion() { return preguntasOrganizacion; }

    public void setPreguntasOrganizacion(List<Pregunta> preguntasOrganizacion) { this.preguntasOrganizacion = preguntasOrganizacion; }

    // Constructor
    public Organizacion() {}


    // Metodos
    public boolean aceptaCaracteristica(String caracteristicaMascota) {
        if(this.existeCaracteristica(caracteristicaMascota)){
            String caracteristica = this.buscarCaracteristica(caracteristicaMascota);
            return caracteristica.equals(caracteristicaMascota);
        }
        else {
            return false;
        }
    }

    private String buscarCaracteristica(String nombreCaracteristica) {
        return caracteristicasAdmitidas.stream().filter(caracteristica -> caracteristica.equals(nombreCaracteristica)).collect(Collectors.toList()).get(0);
    }

    private boolean existeCaracteristica(String nombreCaracteristica) {
        return caracteristicasAdmitidas.stream().anyMatch(caracteristica -> caracteristica.equals(nombreCaracteristica));
    }

    public void crearPregunta(String pregunta) {
        Pregunta nuevaPregunta = new Pregunta(pregunta);
        this.preguntasOrganizacion.add(nuevaPregunta);
    }
}
