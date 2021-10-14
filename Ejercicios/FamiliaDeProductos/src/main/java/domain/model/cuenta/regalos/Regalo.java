package domain.model.cuenta.regalos;

public abstract class Regalo {
    private String nombre;
    private String descripcion;

    // Getters and Setters
    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }


}
