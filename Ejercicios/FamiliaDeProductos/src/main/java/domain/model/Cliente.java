package domain.model;

import domain.model.cuenta.*;

public class Cliente {
    private int edad;
    private boolean pension;
    private Cuenta cuenta = null;

    // Getters and Setters
    public int getEdad() { return edad; }

    public void setEdad(int edad) { this.edad = edad; }

    public boolean isPension() { return pension; }

    public void setPension(boolean pension) { this.pension = pension; }

    public Cuenta getCuenta() { return cuenta; }

    public void setCuenta(Cuenta cuenta) { this.cuenta = cuenta; }

    public Cliente() {}


    // Metodos
    public void abrirCuenta() {
        if(this.getEdad() <= 25) {
            this.setCuenta(new CuentaJoven());
        }
        else if(this.entre(26, 65, this.getEdad()) && this.isPension()) {
            this.setCuenta(new Cuenta10());
        }
        else if(this.getEdad() > 65 && this.isPension()) {
            this.setCuenta(new CuentaOro());
        }
        else {
            this.setCuenta(new CuentaEstandar());
        }
    }

    private boolean entre(int referencia1, int referencia2, int valor) {
        return valor >= referencia1 && valor <= referencia2;
    }

    public void administrarCuenta() {

    }
}
