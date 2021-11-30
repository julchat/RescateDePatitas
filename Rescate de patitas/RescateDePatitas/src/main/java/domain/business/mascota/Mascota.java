package domain.business.mascota;

import domain.business.EntidadPersistente;
import domain.business.caracteristicas.Caracteristica;
import domain.business.foto.Foto;
import domain.business.organizaciones.Organizacion;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "mascota")
public class Mascota extends EntidadPersistente {

    private String nombreMascota;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoAnimal")
    private TipoAnimal tipoAnimal;

    private String apodoMascota;
    private int edadMascota;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexoMascota")
    private SexoMascota sexoMascota;

    private String descripcionMascota;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fotos")
    private List<Foto> fotos = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "caracteristicasMascota")
    private List<Caracteristica> caracteristicasMascota = new ArrayList<>();


    // Constructor
    public Mascota() {}


    // Getters and Setters
    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getApodoMascota() {
        return apodoMascota;
    }

    public void setApodoMascota(String apodoMascota) {
        this.apodoMascota = apodoMascota;
    }

    public int getEdadMascota() {
        return edadMascota;
    }

    public void setEdadMascota(int edadMascota) {
        this.edadMascota = edadMascota;
    }

    public SexoMascota getSexoMascota() {
        return sexoMascota;
    }

    public void setSexoMascota(SexoMascota sexoMascota) {
        this.sexoMascota = sexoMascota;
    }

    public String getDescripcionMascota() {
        return descripcionMascota;
    }

    public void setDescripcionMascota(String descripcionMascota) {
        this.descripcionMascota = descripcionMascota;
    }

    public List<Foto> getFotos() {
        return this.fotos;
    }

    public void setFotos(List<Foto> foto) {
        this.fotos = fotos;
    }

    public List<Caracteristica> getCaracteristicasMascota() {
        return caracteristicasMascota;
    }

    public void setCaracteristicasMascota(List<Caracteristica> caracteristicasMascota) {
        this.caracteristicasMascota = caracteristicasMascota;
    }

    public void quitarCaracteristica(Caracteristica caracteristicaAQuitar) {
        if(this.caracteristicasMascota.contains(caracteristicaAQuitar)) {
            this.caracteristicasMascota.remove(caracteristicaAQuitar);
        }
    }

    public void agregarCaracteristica(Caracteristica caracteristica) { this.caracteristicasMascota.add(caracteristica); }


    // Metodos
    // Esto despues se puede sacar
    public void mostrarDatosMascota() {
        System.out.println("Tipo del Animal: " + getTipoAnimal());
        System.out.println("Nombre de la Mascota: " + getNombreMascota());
        System.out.println("Apodo de la Mascota: " + getApodoMascota());
        System.out.println("Edad de la Mascota: " + getEdadMascota());
        System.out.println("Sexo de la Mascota: " + getSexoMascota());
        System.out.println("Descripción de la Mascota: " + getDescripcionMascota());
        System.out.println("Características de la Mascota: " + getCaracteristicasMascota());
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();
    }
}
