package domain.business;

import domain.business.foto.Foto;
import domain.business.organizaciones.HogarDeTransito;

import java.util.List;

public class MascotaPerdida {
    private List<Foto> carrouselFotos;
    private String descripcion;
    private TipoAnimal tipoAnimal;
    private Tamanio tamanio;
    private Domicilio lugarDeTransito;
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

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public Tamanio getTamanio() {
        return tamanio;
    }

    public void setTamanio(Tamanio tamanio) {
        this.tamanio = tamanio;
    }

    public Domicilio getLugarDeTransito() {
        return lugarDeTransito;
    }

    public void setLugarDeTransito(Domicilio lugarDeTransito) {
        this.lugarDeTransito = lugarDeTransito;
    }

    public Ubicacion getUbicacionEncontrada() {
        return ubicacionEncontrada;
    }

    public void setUbicacionEncontrada(Ubicacion ubicacionEncontrada) {
        this.ubicacionEncontrada = ubicacionEncontrada;
    }

    public void ocuparResidencia(Domicilio lugarDeTransito) {
        this.setLugarDeTransito(lugarDeTransito);
    }

    public void mostrarMascota() {
        System.out.println("Tipo de Animal: " + this.getTipoAnimal());
        System.out.println("Tamaño del animal: " + this.getTamanio());
        System.out.println("Descripción de la Mascota: " + this.getDescripcion());
        System.out.println("Ubicación encontrada: " + this.getUbicacionEncontrada());
        System.out.println("Hogar de Tránsito actual: " + this.getLugarDeTransito());
    }
}
