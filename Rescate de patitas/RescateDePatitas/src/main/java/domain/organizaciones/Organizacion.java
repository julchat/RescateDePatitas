package domain.organizaciones;

import domain.business.caracteristicas.Caracteristica;
import domain.business.foto.Foto;

import java.util.Date;
import java.util.List;

public class Organizacion {
    private String nombreOrganizacion;
    private Date fechaDeCreacion;
    private List<Caracteristica> caracteristicasAdmitidas;
    private Foto logo;

    // Getters and Setters
    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public List<Caracteristica> getCaracteristicasAdmitidas() {
        return caracteristicasAdmitidas;
    }

    public void setCaracteristicasAdmitidas(List<Caracteristica> caracteristicasAdmitidas) {
        this.caracteristicasAdmitidas = caracteristicasAdmitidas;
    }

    public void agregarCaracteristica(Caracteristica caracteristica) {
        this.caracteristicasAdmitidas.add(caracteristica);
    }

    public void quitarCaracteristica(Caracteristica caracteristica) {
        this.caracteristicasAdmitidas.remove(caracteristica);
    }

    public Foto getLogo() {
        return logo;
    }

    public void setLogo(Foto logo) {
        this.logo = logo;
    }

    // Constructor
    public Organizacion() {}

    public Organizacion(String nombreOrganizacion, Date fechaDeCreacion, List<Caracteristica> caracteristicasAdmitidas, Foto logo) {
        this.nombreOrganizacion = nombreOrganizacion;
        this.fechaDeCreacion = fechaDeCreacion;
        this.caracteristicasAdmitidas = caracteristicasAdmitidas;
        this.logo = logo;
    }


}
