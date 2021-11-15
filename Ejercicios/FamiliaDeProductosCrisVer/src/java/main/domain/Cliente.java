package java.main.domain;

import java.main.domain.creacion.*;

public class Cliente {
    String nombre;
    String apellido;
    int edad;
    int nomina;
    int pension;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNomina() {
        return nomina;
    }

    public void setNomina(int nomina) {
        this.nomina = nomina;
    }

    public int getPension() {
        return pension;
    }

    public void setPension(int pension) {
        this.pension = pension;
    }

    public Cuenta crearCuenta(){
        CuentaAbstractFactory factoryUtilizado;
        if (edad < 25){
            factoryUtilizado = new JovenAbstract();
        } else if (edad < 65 && nomina != 0){
            factoryUtilizado = new DiezAbstract();
        }
        else if (edad >= 65 && pension != 0){
            factoryUtilizado = new OroAbstract();
        }
        else {
            factoryUtilizado = new EstandarAbstract();
        }
        factoryUtilizado.prepararCuenta(this);
        factoryUtilizado.agregarTarjetaCredito();
        factoryUtilizado.configurarDescubierto();
        factoryUtilizado.configurarIntereses();
        factoryUtilizado.prepararRegalo();
        factoryUtilizado.agregarTarjetaDebito();
        return factoryUtilizado.armarCuenta();
    }
}
