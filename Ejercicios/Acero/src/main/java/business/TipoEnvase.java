package business;

public class TipoEnvase {
    private int capacidad;
    private String descripcion;
    private int stock;
    private List<Articulo> articulosCompatibles;

    // Getters and Setters
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Articulo> getArticulosCompatibles{
        return articulosCompatibles;
    }

    public void setArticulosCompatibles(List<Articulo> articulosCompatibles) {
        this.articulosCompatibles = articulosCompatibles;
    }

    // Constructor
    public TipoEnvase(){

    }

    public TipoEnvase(int capacidad, String descripcion, int stock) {
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.stock = stock;
    }
}
