package domain.business.publicaciones;

import domain.business.Persona;

import java.util.List;

public class BusquedaMascotaIdeal extends TipoPublicacion{
    private List<String> comodidades;
    private List<String> preferencias;

    public void agregarComodidad(String nuevaComodidad) {
        this.comodidades.add(nuevaComodidad);
    }

    public void agregarPreferencia(String nuevaPreferencia) {
        this.preferencias.add(nuevaPreferencia);
    }

    public List<String> obtenerComodidades() {
        return this.comodidades;
    }

    public List<String> obtenerPreferencias() {
        return this.preferencias;
    }

    public BusquedaMascotaIdeal() {}

    @Override
    public void mostrarDatos() {
        System.out.println("Comodidades: " + obtenerComodidades());
        System.out.println("Preferencias: " + obtenerPreferencias());
    }
}
