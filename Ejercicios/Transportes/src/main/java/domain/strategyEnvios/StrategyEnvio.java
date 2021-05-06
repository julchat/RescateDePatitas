package domain.strategyEnvios;

import domain.Envio;

public interface StrategyEnvio {
    public int calcularPrecio(Envio envio);
}
