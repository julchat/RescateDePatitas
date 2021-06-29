package domain.business.caracteristicas;

import domain.organizaciones.Organizacion;

public class CaracteristicaConValor {
    private Caracteristica nombreCaracteristica;
    private String eleccion;

    // Getters and Setters
    public Caracteristica getNombreCaracteristica() {
        return nombreCaracteristica;
    }

    public void setNombreCaracteristica(Caracteristica nombreCaracteristica) {
        this.nombreCaracteristica = nombreCaracteristica;
    }

    public String getEleccion() {
        return eleccion;
    }

    public void setEleccion(String eleccion) {
        this.eleccion = eleccion;
    }


    // Constructor
    public CaracteristicaConValor() {}

    public CaracteristicaConValor(Caracteristica nombreCaracteristica, String eleccion) {
        this.nombreCaracteristica = nombreCaracteristica;
        this.eleccion = eleccion;
    }

    public boolean soyCaracteristicaValida(Organizacion organizacion) {
        return organizacion.aceptoCaracteristica(this);
    }


}
