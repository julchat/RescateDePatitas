package business;

import java.time.LocalDate;
import java.util.List;

public class Entrega {
    private Articulo articulo;
    private Envase envase;
    private int cantidadPedida;
    private List<Articulo> articulos;

    // Getters and Setters
    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Envase getEnvase() {
        return envase;
    }

    public void setEnvase(Envase envase) {
        this.envase = envase;
    }

    public int getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(int cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

   public void agregarArticulo(Articulo articulo) {
        this.articulos.add(articulo);
   }

    // Constructor
    public Entrega(){}

    public Entrega(Articulo articulo, Envase envase, int cantidadPedida, List<Articulo> articulos) {
        this.articulo = articulo;
        this.envase = envase;
        this.cantidadPedida = cantidadPedida;
        this.articulos = articulos;
    }

    // Métodos
    public void ingresarArticulo(int codArticulo, int cantidadArticulos, int toleranciaAncho, int toleranciaAlto, int toleranciaLargo){
    }

    public LocalDate calcularFechaEntrega(int numeroEntrega, LocalDate fechaInicioEntrega, IntervaloRepeticion repeticion) {
        if(repeticion == IntervaloRepeticion.UNICA_VEZ) {
            return fechaInicioEntrega;
        }
        if(repeticion == IntervaloRepeticion.SEMANAL) {
            return fechaInicioEntrega.plusDays(7);
        }
        if(repeticion == IntervaloRepeticion.MENSUAL) {
            return fechaInicioEntrega.plusDays(30);
        }
        else {
            return null;
        }
    }
}
