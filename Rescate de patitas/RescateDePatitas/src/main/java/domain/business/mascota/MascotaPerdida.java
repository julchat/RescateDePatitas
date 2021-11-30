package domain.business.mascota;

import domain.business.EntidadPersistente;
import domain.business.caracteristicas.Caracteristica;
import domain.business.ubicacion.Lugar;
import domain.business.foto.Foto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "mascota_perdida")
public class MascotaPerdida extends EntidadPersistente {

    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoAnimal")
    private TipoAnimal tipoAnimal;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexoMascota")
    private SexoMascota sexoMascota;

    @Enumerated(EnumType.STRING)
    @Column(name = "tamanio")
    private Tamanio tamanio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lugarDeTransito")
    private Lugar lugarDeTransito;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "caracteristicasMascota")
    private List<Caracteristica> caracteristicasMascota = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lugarEncontrada")
    private Lugar lugarEncontrada;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrouselFotos")
    private List<Foto> carrouselFotos = new ArrayList<>();


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

    public SexoMascota getSexoMascota() { return sexoMascota; }

    public void setSexoMascota(SexoMascota sexoMascota) { this.sexoMascota = sexoMascota; }

    public Tamanio getTamanio() { return tamanio; }

    public void setTamanio(Tamanio tamanio) { this.tamanio = tamanio; }

    public Lugar getLugarDeTransito() {
        return lugarDeTransito;
    }

    public void setLugarDeTransito(Lugar lugarDeTransito) { this.lugarDeTransito = lugarDeTransito; }

    public Lugar getLugarEncontrada() { return lugarEncontrada; }

    public void setLugarEncontrada(Lugar lugarEncontrada) { this.lugarEncontrada = lugarEncontrada; }

    public List<Caracteristica> getCaracteristicaMascotas() { return caracteristicasMascota; }

    public void setCaracteristicaMascotas(List<Caracteristica> caracteristicasMascota) { this.caracteristicasMascota = caracteristicasMascota; }


    // Metodos
    // TODO: arreglar
    public boolean cumpleCaracteristicaHogar(Caracteristica caracteristica) {
        return caracteristicasMascota.stream().anyMatch(caracteristicaMascotas -> caracteristica.equals(caracteristica));
    }

    public void ocuparLugarDeTransito(Lugar lugarAOcupar) {
        this.setLugarDeTransito(lugarAOcupar);
    }

    public void mostrarMascota() {
        System.out.println("Tipo de Animal: " + this.getTipoAnimal());
        System.out.println("Tamaño del animal: " + this.getTamanio());
        System.out.println("Descripción de la Mascota: " + this.getDescripcion());
        System.out.println("Ubicación encontrada:");
        System.out.println("    - Latitud: " + this.getLugarEncontrada().getLatitud());
        System.out.println("    - Longitud: " + this.getLugarEncontrada().getLongitud());
        System.out.println("Hogar de Tránsito actual: " + this.getLugarDeTransito());
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();
    }
}
