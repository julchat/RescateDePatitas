package domain;

public class Observador1 implements Observador {

    @Override
    public void updateCarga(Bateria bateria) {
        System.out.println("La bateria tiene una carga de " + bateria.getCarga() + "%.");
    }

    @Override
    public void updateTiempo(Bateria bateria) {
        System.out.println("La bateria le queda un tiempo de " + bateria.getTiempo() + " restante.");
    }
}
