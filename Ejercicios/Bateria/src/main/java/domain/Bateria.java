package domain;

import java.util.List;

public class Bateria {
    private boolean conectada;
    private boolean cargando;
    private int carga;
    private int tiempo;
    private List<Observador> observadores;

    // Getters and Setters
    public boolean estaConectada() {
        return conectada;
    }

    public boolean estaCargando() {
        return cargando;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void agregarObservador(Observador nuevoObservador) {
        this.observadores.add(nuevoObservador);
    }

    public void removerObservador(Observador observador) {
        this.observadores.remove(observador);
    }


    // Constructor
    public Bateria() {}

    public Bateria(boolean conectada, boolean cargando, int carga, int tiempo, List<Observador> observadores) {
        this.conectada = conectada;
        this.cargando = cargando;
        this.carga = carga;
        this.tiempo = tiempo;
        this.observadores = observadores;
    }

    // Metodos

    public void notificar(){
        this.observadores.forEach(observador -> observador.updateCarga(this));
        this.observadores.forEach(observador -> observador.updateTiempo(this));
    }

    public void desconectar() {
        this.conectada = false;
        this.cargando = false;
    }

    public void conectar() {
        this.conectada = true;
        this.cargando = true;
    }


}
