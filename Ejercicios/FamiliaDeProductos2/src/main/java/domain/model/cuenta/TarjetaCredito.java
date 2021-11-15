package domain.model.cuenta;

public class TarjetaCredito {
    private int saldo;
    private double porcentajePension;


    // Getters and Setters
    public int getSaldo() { return saldo; }

    public void setSaldo(int saldo) { this.saldo = saldo; }

    public double getPorcentajePension() { return porcentajePension; }

    public void setPorcentajePension(double porcentajePension) { this.porcentajePension = porcentajePension; }


    // Constructor
    public TarjetaCredito(int saldo, double porcentajePension) {
        this.saldo = saldo;
        this.porcentajePension = porcentajePension;
    }
}
