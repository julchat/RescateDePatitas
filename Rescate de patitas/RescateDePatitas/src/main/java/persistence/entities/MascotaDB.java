package persistence.entities;

import domain.business.SexoMascota;
import domain.business.TipoAnimal;
import domain.business.foto.Foto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mascota")
public class MascotaDB extends EntidadPersistente {

    @Column(name = "nombre")
    private String nombreMascota;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_animal")
    private TipoAnimal tipoAnimal;

    @Column(name = "apodo")
    private String apodoMascota;

    @Column(name = "edad")
    private int edadMascota;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private SexoMascota sexoMascota;

    @Column(name = "descripcion")
    private String descripcionMascota;

    //@OneToMany(cascade = CascadeType.PERSIST)
    //@Column(name = "Fotos")
    @Transient
    private List<Foto> fotos = new ArrayList<Foto>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "caracteristicas_mascotas")
    private List<CaracteristicaDB> caracteristicasMascota = new ArrayList<>();


    // Getters and Setters
    public String getNombreMascota() { return nombreMascota; }

    public void setNombreMascota(String nombreMascota) { this.nombreMascota = nombreMascota; }

    public TipoAnimal getTipoAnimal() { return tipoAnimal; }

    public void setTipoAnimal(TipoAnimal tipoAnimal) { this.tipoAnimal = tipoAnimal; }

    public String getApodoMascota() { return apodoMascota; }

    public void setApodoMascota(String apodoMascota) { this.apodoMascota = apodoMascota; }

    public int getEdadMascota() { return edadMascota; }

    public void setEdadMascota(int edadMascota) { this.edadMascota = edadMascota; }

    public SexoMascota getSexoMascota() { return sexoMascota; }

    public void setSexoMascota(SexoMascota sexoMascota) { this.sexoMascota = sexoMascota; }

    public String getDescripcionMascota() { return descripcionMascota; }

    public void setDescripcionMascota(String descripcionMascota) { this.descripcionMascota = descripcionMascota; }

    public List<Foto> getFotos() { return fotos; }

    public void setFotos(List<Foto> fotos) { this.fotos = fotos; }

    public List<CaracteristicaDB> getCaracteristicasMascota() { return caracteristicasMascota; }

    public void setCaracteristicasMascota(List<CaracteristicaDB> caracteristicasMascota) { this.caracteristicasMascota = caracteristicasMascota; }
}
