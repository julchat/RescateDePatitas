package domain.business.publicaciones;

import domain.business.users.Persona;

import java.util.List;

public class PublicacionParaAdoptar extends Publicacion{
    private List<String> comodidades;
    private List<String> preferencias;

    // Getters and Setters
    public void setComodidades(List<String> comodidades) { this.comodidades = comodidades; }

    public void agregarComodidad(String nuevaComodidad) {
        this.comodidades.add(nuevaComodidad);
    }

    public void setPreferencias(List<String> preferencias) { this.preferencias = preferencias; }

    public void agregarPreferencia(String nuevaPreferencia) {
        this.preferencias.add(nuevaPreferencia);
    }

    public List<String> obtenerComodidades() {
        return this.comodidades;
    }

    public List<String> obtenerPreferencias() {
        return this.preferencias;
    }


    // Métodos
    public void crearPublicacion(Persona autor, List<String> comodidades, List<String> preferencias) {
        super.crearPublicacion(new Pendiente());
        this.setAutor(autor);
        this.setComodidades(comodidades);
        this.setPreferencias(preferencias);
    }

    @Override
    public void mostrarPublicacion() {
        this.getAutor().mostrarDatosNoSensibles();
        System.out.println("Comodidades: " + obtenerComodidades());
        System.out.println("Preferencias: " + obtenerPreferencias());
    }
}
