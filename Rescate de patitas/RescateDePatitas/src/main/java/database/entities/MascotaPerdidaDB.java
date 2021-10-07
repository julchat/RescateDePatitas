package database.entities;


import domain.business.mascota.Tamanio;
import domain.business.mascota.TipoAnimal;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mascota_perdida")
public class MascotaPerdidaDB extends EntidadPersistente {

    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_animal")
    private TipoAnimal tipoAnimal;

    @Enumerated(EnumType.STRING)
    @Column(name = "tamanio")
    private Tamanio tamanio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lugar_de_transito")
    private LugarDB lugarDeTransito;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "caracteristicas_mascota_perdida")
    private List<CaracteristicaDB> caracteristicaMascotaPerdida = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "ubicacion_id")
    private UbicacionDB ubicacionEncontrada;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fotos_mascota_perdida")
    private List<FotoDB> carrouselFotos = new ArrayList<>();


// Getters and Setters
    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public TipoAnimal getTipoAnimal() { return tipoAnimal; }

    public void setTipoAnimal(TipoAnimal tipoAnimal) { this.tipoAnimal = tipoAnimal; }

    public Tamanio getTamanio() { return tamanio; }

    public void setTamanio(Tamanio tamanio) { this.tamanio = tamanio; }

    public LugarDB getLugarDeTransito() { return lugarDeTransito; }

    public void setLugarDeTransito(LugarDB lugarDeTransito) { this.lugarDeTransito = lugarDeTransito; }

    public List<CaracteristicaDB> getCaracteristicaMascotas() { return caracteristicaMascotaPerdida; }

    public void setCaracteristicaMascotas(List<CaracteristicaDB> caracteristicaMascotas) { this.caracteristicaMascotaPerdida = caracteristicaMascotas; }

    public List<FotoDB> getCarrouselFotos() { return carrouselFotos; }

    public void setCarrouselFotos(List<FotoDB> carrouselFotos) { this.carrouselFotos = carrouselFotos; }
}