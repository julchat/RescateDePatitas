package persistence.entities;

import domain.business.SexoMascota;
import domain.business.TipoAnimal;
import domain.business.foto.Foto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Mascota")
public class MascotaDB extends EntidadPersistente {

    @Column(name = "Nombre")
    private String nombreMascota;

    @Enumerated(EnumType.STRING)
    @Column(name = "Tipo Animal")
    private TipoAnimal tipoAnimal;

    @Column(name = "Apodo")
    private String apodoMascota;

    @Column(name = "Edad")
    private int edadMascota;

    @Enumerated(EnumType.STRING)
    @Column(name = "Sexo Animal")
    private SexoMascota sexoMascota;

    @Column(name = "Descripcion")
    private String descripcionMascota;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Column(name = "Fotos")
    private List<Foto> fotos = new ArrayList<Foto>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @Column(name = "Caracteristicas")
    private List<CaracteristicaDB> caracteristicasMascota = new ArrayList<CaracteristicaDB>();

    @Column(name = "¿Esta perdida?")
    private boolean estaPerdida;

    // TODO: duda en esta, si es estaAdoptada o estaDisponibleParaAdoptar
    @Column(name = "Disponible para adoptar")
    private boolean estaAdoptada;

    @OneToOne
    @JoinColumn(name = "Encargado")
    private PersonaDB encargado;


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

    public boolean isEstaPerdida() { return estaPerdida; }

    public void setEstaPerdida(boolean estaPerdida) { this.estaPerdida = estaPerdida; }

    public boolean isEstaAdoptada() { return estaAdoptada; }

    public void setEstaAdoptada(boolean estaAdoptada) { this.estaAdoptada = estaAdoptada; }

    public PersonaDB getEncargado() { return encargado; }

    public void setEncargado(PersonaDB encargado) { this.encargado = encargado; }
}
