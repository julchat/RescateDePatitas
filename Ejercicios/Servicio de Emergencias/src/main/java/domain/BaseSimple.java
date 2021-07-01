package domain;

public class BaseSimple extends Base {
    private int cantidadAmbulancias;
    private float tiempoMedio;

    // Getters and Setters
    public int getCantidadAmbulancias() {
        return cantidadAmbulancias;
    }

    public void setCantidadAmbulancias(int cantidadAmbulancias) {
        this.cantidadAmbulancias = cantidadAmbulancias;
    }

    public void setTiempoMedio(float tiempoMedio) {
        this.tiempoMedio = tiempoMedio;
    }

    public float getTiempoMedio() {
        return tiempoMedio;
    }

    // Constructores
    public BaseSimple() { }

    public BaseSimple(String nombreBase, int cantidadAmbulancias, float tiempoMedio) {
        super(nombreBase);
        this.cantidadAmbulancias = cantidadAmbulancias;
        this.tiempoMedio = tiempoMedio;
    }

    @Override
    public int cantidadDeAmbulancias() {
        return this.getCantidadAmbulancias();
    }

    @Override
    public float tiempoMedio() {
        return this.getTiempoMedio();
    }
}
