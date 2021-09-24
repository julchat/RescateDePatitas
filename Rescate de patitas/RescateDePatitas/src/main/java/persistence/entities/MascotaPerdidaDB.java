package persistence.entities;

import domain.business.Tamanio;
import domain.business.TipoAnimal;
import domain.business.Ubicacion;
import domain.business.foto.Foto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Mascota Perdida")
public class MascotaPerdidaDB extends EntidadPersistente {

    @Column(name = "Descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "Tipo de Animal")
    private TipoAnimal tipoAnimal;

    @Enumerated(EnumType.STRING)
    @Column(name = "Tamaño de Animal")
    private Tamanio tamanio;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Lugar de Tránsito")
    private LugarDB lugarDeTransito;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Caracteristicas de la Mascota")
    private List<CaracteristicaDB> caracteristicaMascotas = new ArrayList<CaracteristicaDB>();

    @Column(name = "Ubicacion encontrada")
    @Convert(converter = Ubicacion.class)
    private Ubicacion ubicacionEncontrada;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Column(name = "Carrousel de Fotos")
    private List<Foto> carrouselFotos = new ArrayList<Foto>();


// Getters and Setters
    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public TipoAnimal getTipoAnimal() { return tipoAnimal; }

    public void setTipoAnimal(TipoAnimal tipoAnimal) { this.tipoAnimal = tipoAnimal; }

    public Tamanio getTamanio() { return tamanio; }

    public void setTamanio(Tamanio tamanio) { this.tamanio = tamanio; }

    public LugarDB getLugarDeTransito() { return lugarDeTransito; }

    public void setLugarDeTransito(LugarDB lugarDeTransito) { this.lugarDeTransito = lugarDeTransito; }

    public List<CaracteristicaDB> getCaracteristicaMascotas() { return caracteristicaMascotas; }

    public void setCaracteristicaMascotas(List<CaracteristicaDB> caracteristicaMascotas) { this.caracteristicaMascotas = caracteristicaMascotas; }

    public Ubicacion getUbicacionEncontrada() { return ubicacionEncontrada; }

    public void setUbicacionEncontrada(Ubicacion ubicacionEncontrada) { this.ubicacionEncontrada = ubicacionEncontrada; }

    public List<Foto> getCarrouselFotos() { return carrouselFotos; }

    public void setCarrouselFotos(List<Foto> carrouselFotos) { this.carrouselFotos = carrouselFotos; }
}
