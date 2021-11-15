package java.main.domain;

public class TarjetaDebito {
    long numero;
    Cuenta duenio;

    public TarjetaDebito(Cuenta cuenta) {
        this.duenio = cuenta;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public Cuenta getDuenio() {
        return duenio;
    }

    public void setDuenio(Cuenta duenio) {
        this.duenio = duenio;
    }

    public void realizarCompra(int valor){
        duenio.deducirSaldo(valor);
    }
}
