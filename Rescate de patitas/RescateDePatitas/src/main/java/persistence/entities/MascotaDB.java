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

    private String nombreMascota;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_animal")
    private TipoAnimal tipoAnimal;

    private String apodoMascota;
    private int edadMascota;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo_mascota")
    private SexoMascota sexoMascota;

    private String descripcionMascota;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fotos_mascota")
    private List<FotoDB> fotos = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "caracteristicas_mascota")
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

    public List<FotoDB> getFotos() { return fotos; }

    public void setFotos(List<FotoDB> fotos) { this.fotos = fotos; }

    public List<CaracteristicaDB> getCaracteristicasMascota() { return caracteristicasMascota; }

    public void setCaracteristicasMascota(List<CaracteristicaDB> caracteristicasMascota) { this.caracteristicasMascota = caracteristicasMascota; }
}
