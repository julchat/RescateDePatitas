package domain;

import domain.strategyEnvios.StrategyEnvio;

public class Envio {
    private Direccion direccionDesde;
    private Direccion direccionHasta;
    private Paquete paquete;
    private StrategyEnvio estrategiaEnvio;

    // Getters and Setters
    public Direccion getDireccionDesde() {
        return direccionDesde;
    }

    public void setDireccionDesde(Direccion direccionDesde) {
        this.direccionDesde = direccionDesde;
    }

    public Direccion getDireccionHasta() {
        return direccionHasta;
    }

    public void setDireccionHasta(Direccion direccionHasta) {
        this.direccionHasta = direccionHasta;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public StrategyEnvio getEstrategiaEnvio() { return estrategiaEnvio;}

    public void setEstrategiaEnvio(StrategyEnvio estrategiaEnvio) { this.estrategiaEnvio = estrategiaEnvio;}
    // Constructor
    public Envio(Direccion direccionDesde, Direccion direccionHasta, Paquete paquete, StrategyEnvio estrategiaEnvio) {
        this.direccionDesde = direccionDesde;
        this.direccionHasta = direccionHasta;
        this.paquete = paquete;
        this.estrategiaEnvio = estrategiaEnvio;
    }

    public Envio() {
    }

    // Método CalcularPrecio
    public int calcularPrecio(Envio envio) {
        return estrategiaEnvio.calcularPrecio(this);
    }
}
