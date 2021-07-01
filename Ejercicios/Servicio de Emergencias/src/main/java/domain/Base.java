package domain;

public class Base {
    private String nombreBase;

    // Getters and Setters
    public String getNombreBase() {
        return nombreBase;
    }

    public void setNombreBase(String nombreBase) {
        this.nombreBase = nombreBase;
    }

    // Constructor
    public Base() {}

    public Base(String nombreBase) {
        this.nombreBase = nombreBase;
    }

    public int cantidadDeAmbulancias() {
        return 0;
    }

    public float tiempoMedio() {
        return 0;
    }
}
