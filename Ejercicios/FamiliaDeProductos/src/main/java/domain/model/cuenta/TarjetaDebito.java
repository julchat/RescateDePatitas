package domain.model.cuenta;

public class TarjetaDebito {
    private int saldo;


    // Getters and Setters
    public int getSaldo() { return saldo; }

    public void setSaldo(int saldo) { this.saldo = saldo; }


    // Constructor
    public TarjetaDebito(int saldo) {
        this.saldo = saldo;
    }
}
