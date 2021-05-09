package domain;

public class Cuenta {
    private int saldo;

    // Getter and Setter
    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    // Constructor
    public Cuenta(int saldo) {
        this.saldo = saldo;
    }

    // Metodos
    public boolean superaMonto(int saldoPiso) {
        return this.getSaldo() > saldoPiso;
    }
}
