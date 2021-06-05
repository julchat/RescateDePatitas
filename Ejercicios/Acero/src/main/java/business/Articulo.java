package business;

public class Articulo {
    private int codArticulo;
    private String nombre;
    private float precioUnitario;
    private int cantStockActual;
    private TipoEnvase envaseDefault;
    private float largo;
    private float ancho;
    private float alto;

    // Getters and Setters
    public int getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantStockActual() {
        return cantStockActual;
    }

    public void setCantStockActual(int cantStockActual) {
        this.cantStockActual = cantStockActual;
    }

    public TipoEnvase getEnvaseDefault() {
        return envaseDefault;
    }

    public void setEnvaseDefault(TipoEnvase envaseDefault) {
        this.envaseDefault = envaseDefault;
    }

    public float getLargo() {
        return largo;
    }

    public void setLargo(float largo) {
        this.largo = largo;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }


    // Constructor
    public Articulo(){

    }

    public Articulo(int codArticulo, String nombre, float precioUnitario, int cantStockActual, TipoEnvase envaseDefault, float largo, float ancho, float alto) {
        this.codArticulo = codArticulo;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.cantStockActual = cantStockActual;
        this.envaseDefault = envaseDefault;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
    }

    // Métodos
    public boolean soyCompatibleCon(TipoEnvase tipoEnvase) {
        return tipoEnvase.getArticulosCompatibles().stream().anyMatch(unArticulo -> unArticulo == this);
    }
}
