package domain.business;

import domain.business.caracteristicas.Caracteristica;
import domain.business.caracteristicas.CaracteristicaMascota;
import domain.business.foto.Foto;

import java.util.ArrayList;
import java.util.List;

public class MascotaPerdida {
    private List<Foto> carrouselFotos;
    private String descripcion;
    private TipoAnimal tipoAnimal;
    private Tamanio tamanio;
    private Lugar lugarDeTransito;
    private List<String> caracteristicas = new ArrayList<>();
    private Ubicacion ubicacionEncontrada;


    // Getters and Setters
    public List<Foto> getCarrouselFotos() {
        return carrouselFotos;
    }

    public void setCarrouselFotos(List<Foto> carrouselFotos) {
        this.carrouselFotos = carrouselFotos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoAnimal getTipoAnimal() { return tipoAnimal; }

    public void setTipoAnimal(TipoAnimal tipoAnimal) { this.tipoAnimal = tipoAnimal; }

    public Tamanio getTamanio() { return tamanio; }

    public void setTamanio(Tamanio tamanio) { this.tamanio = tamanio; }

    public Lugar getLugarDeTransito() {
        return lugarDeTransito;
    }

    public void setLugarDeTransito(Lugar lugarDeTransito) { this.lugarDeTransito = lugarDeTransito; }

    public Ubicacion getUbicacionEncontrada() {
        return ubicacionEncontrada;
    }

    public void setUbicacionEncontrada(Ubicacion ubicacionEncontrada) { this.ubicacionEncontrada = ubicacionEncontrada; }

    public List<String> getCaracteristicaMascotas() { return caracteristicas; }

    public void setCaracteristicaMascotas(List<String> caracteristicaMascotas) { this.caracteristicas = caracteristicaMascotas; }


    // Metodos
    public boolean cumpleCaracteristicaHogar(String caracteristica) {
        return caracteristicas.stream().anyMatch(caracteristicaMascotas -> caracteristica.equals(caracteristica));
    }

    public void ocuparLugarDeTransito(Lugar lugarAOcupar) {
        this.setLugarDeTransito(lugarAOcupar);
    }

    public void mostrarMascota() {
        //System.out.println("Tipo de Animal: " + this.getTipoAnimal());
        //System.out.println("Tamaño del animal: " + this.getTamanio());
        System.out.println("Descripción de la Mascota: " + this.getDescripcion());
        System.out.println("Ubicación encontrada:");
        System.out.println("    - Latitud: " + this.getUbicacionEncontrada().getLatitud());
        System.out.println("    - Longitud: " + this.getUbicacionEncontrada().getLongitud());
        System.out.println("Hogar de Tránsito actual: " + this.getLugarDeTransito());
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();
    }
}
