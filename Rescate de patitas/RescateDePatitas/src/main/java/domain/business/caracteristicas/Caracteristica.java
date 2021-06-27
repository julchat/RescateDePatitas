package domain.business.caracteristicas;

public class Caracteristica {
    private String nombre;

    // Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    // Constructor
    public Caracteristica() {}

    public Caracteristica(String nombre) {
        this.nombre = nombre;
    }
}
