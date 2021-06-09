package domain.caracteristicas;

public class CaracteristicaConValor {
    private String nombreCaracteristica;
    private String eleccion;

    // Getters and Setters
    public String getNombreCaracteristica() {
        return nombreCaracteristica;
    }

    public void setNombreCaracteristica(String nombreCaracteristica) {
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

    public CaracteristicaConValor(String nombreCaracteristica, String eleccion) {
        this.nombreCaracteristica = nombreCaracteristica;
        this.eleccion = eleccion;
    }
}
