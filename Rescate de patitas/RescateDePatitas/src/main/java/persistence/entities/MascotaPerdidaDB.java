package persistence.entities;


import domain.business.Tamanio;
import domain.business.TipoAnimal;
import domain.business.Ubicacion;
import domain.business.foto.Foto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mascota_perdida")
public class MascotaPerdidaDB extends EntidadPersistente {

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_animal")
    private TipoAnimal tipoAnimal;

    @Enumerated(EnumType.STRING)
    @Column(name = "tamanio")
    private Tamanio tamanio;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "lugar_de_transito")
    private LugarDB lugarDeTransito;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "caracteristicas_mascotas_perdida")
    private List<CaracteristicaDB> caracteristicaMascotasPerdida = new ArrayList<>();

    //@Column(name = "Ubicacion encontrada")
    //@Convert(converter = Ubicacion.class)
    @Transient
    private Ubicacion ubicacionEncontrada;

    @Transient
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

    public List<CaracteristicaDB> getCaracteristicaMascotas() { return caracteristicaMascotasPerdida; }

    public void setCaracteristicaMascotas(List<CaracteristicaDB> caracteristicaMascotas) { this.caracteristicaMascotasPerdida = caracteristicaMascotas; }

    public Ubicacion getUbicacionEncontrada() { return ubicacionEncontrada; }

    public void setUbicacionEncontrada(Ubicacion ubicacionEncontrada) { this.ubicacionEncontrada = ubicacionEncontrada; }

    public List<Foto> getCarrouselFotos() { return carrouselFotos; }

    public void setCarrouselFotos(List<Foto> carrouselFotos) { this.carrouselFotos = carrouselFotos; }
}