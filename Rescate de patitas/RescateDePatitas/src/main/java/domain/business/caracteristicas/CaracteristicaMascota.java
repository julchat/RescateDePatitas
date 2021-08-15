package domain.business.caracteristicas;

import domain.business.organizaciones.Organizacion;

public class CaracteristicaMascota {
    private String nombreCaracteristica;
    private String valorElegido;

    // Getters and Setters
    public String getNombreCaracteristica() {
        return nombreCaracteristica;
    }

    public void setNombreCaracteristica(String nombreCaracteristica) {
        this.nombreCaracteristica = nombreCaracteristica;
    }

    public String getValorElegido() {
        return valorElegido;
    }

    public void setValorElegido(String valorElegido) {
        this.valorElegido = valorElegido;
    }

    // Constructor
    public CaracteristicaMascota() {}

    public CaracteristicaMascota(String nombreCaracteristica, String valorElegido) {
        this.nombreCaracteristica = nombreCaracteristica;
        this.valorElegido = valorElegido;
    }


    // Metodos
    public boolean soyCaracteristicaValida(Organizacion organizacion) {
        return organizacion.aceptoCaracteristica(this);
    }

}
