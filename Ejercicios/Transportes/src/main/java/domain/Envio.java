package domain;

import domain.strategyEnvios.StrategyEnvio;

public class Envio {
    private String ciudadDesde;
    private String ciudadHasta;
    private Paquete paquete;
    private StrategyEnvio estrategiaEnvio;

    // Getters and Setters
    public String getCiudadDesde() {
        return ciudadDesde;
    }

    public void setCiudadDesde(String ciudadDesde) {
        this.ciudadDesde = ciudadDesde;
    }

    public String getCiudadHasta() {
        return ciudadHasta;
    }

    public void setCiudadHasta(String ciudadHasta) {
        this.ciudadHasta = ciudadHasta;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    // Constructor
    public Envio(String ciudadDesde, String ciudadHasta, Paquete paquete) {
        this.ciudadDesde = ciudadDesde;
        this.ciudadHasta = ciudadHasta;
        this.paquete = paquete;
    }

    // Método CalcularPrecio
    public int calcularPrecio(Envio envio) {
        return estrategiaEnvio.calcularPrecio(this);
    }
}
