package domain.model.cuenta;

import domain.model.cuenta.regalos.Regalo;

public abstract class Cuenta {
    private double intereses;
    private TarjetaDebito tarjetaDebito;
    private TarjetaCredito tarjetaCredito;
    private Regalo regalo;

    // Getters and Setters
    public double getIntereses() { return intereses; }

    public void setIntereses(double intereses) { this.intereses = intereses; }

    public TarjetaDebito getTarjetaDebito() { return tarjetaDebito; }

    public void setTarjetaDebito(TarjetaDebito tarjetaDebito) { this.tarjetaDebito = tarjetaDebito; }

    public TarjetaCredito getTarjetaCredito() { return tarjetaCredito; }

    public void setTarjetaCredito(TarjetaCredito tarjetaCredito) { this.tarjetaCredito = tarjetaCredito; }

    public Regalo getRegalo() { return regalo; }

    public void setRegalo(Regalo regalo) { this.regalo = regalo; }


    // Metodos
  /*  public abstract boolean cumpleCondicion(Cliente cliente);

    public void asignarCuenta(Cliente cliente) {
        if(cliente.getEdad() <= 25) {
            cliente.setCuenta(new CuentaJoven());
        }
        else if(this.entre(26, 65, cliente.getEdad()) && cliente.isPension()) {
            cliente.setCuenta(new Cuenta10());
        }
        else if(cliente.getEdad() > 65 && cliente.isPension()) {
            cliente.setCuenta(new CuentaOro());
        }
        else {
            cliente.setCuenta(new CuentaEstandar());
        }
    }
    public void actualizarCuenta(Cliente cliente) { }

    private boolean entre(int referencia1, int referencia2, int valor) {
        return valor >= referencia1 && valor <= referencia2;
    }*/
}
