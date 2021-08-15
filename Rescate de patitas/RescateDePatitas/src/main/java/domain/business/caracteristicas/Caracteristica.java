package domain.business.caracteristicas;

import java.util.List;

public class Caracteristica {
    private String nombre;
    private List<String> opcionesValidas;

    // Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getOpcionesValidas() {
        return opcionesValidas;
    }

    public void setOpcionesValidas(List<String> opcionesValidas) {
        this.opcionesValidas = opcionesValidas;
    }

    public void agregarNuevaCaracteristica(String nuevaCaracteristica) {
        this.opcionesValidas.add(nuevaCaracteristica);
    }

    // Constructor
    public Caracteristica() {}

    public Caracteristica(String caracteristica) {
        this.nombre = caracteristica;
        this.opcionesValidas.add(caracteristica);
    }
}
